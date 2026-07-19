package com.bang.authservice.controller;

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
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CreateCategoryRequest request
            ){
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/tree")
    public ResponseEntity<List<CategoryResponse>> getTree() {

        return ResponseEntity.ok(categoryService.getTree());
    }
}
