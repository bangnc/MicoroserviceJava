package com.bang.authservice.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategory exception"),
    CATEGORYNAME_VALID(1003,"Category name must be at lest 8 character"),
    INVALID_KEY(1002,"Invalid key miss"),
    USER_EXISTED(1001, "Category not existed");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
