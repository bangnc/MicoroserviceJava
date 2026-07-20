package com.bang.authservice.dto.request;

import com.bang.authservice.exception.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotBlank(message = "Category name is required")
    @Size(min = 8, message = "CATEGORYNAME_VALID")
    private String name;
    private Long parentId;
}
