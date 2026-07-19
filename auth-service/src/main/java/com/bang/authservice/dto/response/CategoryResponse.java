package com.bang.authservice.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
    private Long id;

    private String name;

    private String slug;

    private Long parentId;

    private Integer level;

    private Integer sortOrder;

    private Integer productCount;

    private List<CategoryResponse> children;

}
