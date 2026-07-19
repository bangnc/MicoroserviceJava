package com.bang.authservice.service;

import com.bang.authservice.dto.request.CreateCategoryRequest;
import com.bang.authservice.dto.response.CategoryResponse;
import com.bang.authservice.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponse  createCategory(CreateCategoryRequest request);
    List<CategoryResponse> getTree();
}
