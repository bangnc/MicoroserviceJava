package com.bang.authservice.service.imp;

import com.bang.authservice.dto.request.CreateCategoryRequest;
import com.bang.authservice.dto.response.CategoryResponse;
import com.bang.authservice.entity.Category;
import com.bang.authservice.exception.AppException;
import com.bang.authservice.exception.ErrorCode;
import com.bang.authservice.repository.CategoryRepository;
import com.bang.authservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse  createCategory(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        if(request.getParentId() != null){

            Category parent =
                    categoryRepository.findById(
                                    request.getParentId()
                            )
                            .orElseThrow(
                                    () -> new AppException(ErrorCode.USER_EXISTED)
                            );


            category.setParent(parent);
            category.setLevel(parent.getLevel()+1);
            category.setPath(
                    parent.getPath()
                            + parent.getId()
                            + "/"
            );

        }
        else {

            category.setLevel(0);
            category.setPath("/");

        }


        Category saved =
                categoryRepository.save(category);


        return mapToResponse(saved);
    }

    @Override
    public List<CategoryResponse> getTree() {
        List<Category> categories = categoryRepository.findAll();

        Map<Long, List<Category>> map = categories.stream()
                .filter(category -> category.getParent() != null)
                .collect(Collectors.groupingBy(category -> category.getParent().getId()));

        return categories.stream()
                .filter(category -> category.getParent() == null)
                .map(category -> buildTree(category, map))
                .toList();
    }

    private CategoryResponse buildTree(
            Category category,
            Map<Long, List<Category>> map) {

        CategoryResponse response = mapToResponse(category);

        List<CategoryResponse> children = map
                .getOrDefault(category.getId(), Collections.emptyList())
                .stream()
                .map(child -> buildTree(child, map))
                .toList();

        response.setChildren(children);

        return response;
    }

    private CategoryResponse mapToResponse(
            Category category
    ){

        CategoryResponse response =
                new CategoryResponse();


        response.setId(category.getId());

        response.setName(category.getName());

        response.setSlug(category.getSlug());

        response.setLevel(category.getLevel());

        response.setSortOrder(category.getSortOrder());

        response.setProductCount(
                category.getProductCount()
        );


        if(category.getParent()!=null){

            response.setParentId(
                    category.getParent().getId()
            );
        }


        return response;
    }
}
