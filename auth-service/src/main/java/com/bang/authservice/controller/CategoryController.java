package com.bang.authservice.controller;

import com.bang.authservice.dto.request.ApiResponse;
import com.bang.authservice.dto.request.CreateCategoryRequest;
import com.bang.authservice.dto.response.CategoryResponse;
import com.bang.authservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory(
             @RequestBody @Valid CreateCategoryRequest request
            ){
        ApiResponse<CategoryResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryService.createCategory(request));
        CategoryResponse response = categoryService.createCategory(request);
        return apiResponse;
    }
    @GetMapping("/tree")
    public ResponseEntity<List<CategoryResponse>> getTree() {

        return ResponseEntity.ok(categoryService.getTree());
    }
}
