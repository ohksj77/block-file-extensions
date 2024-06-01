package com.kimseungjin.block_file_extensions.global.advice;

public record ErrorResponse(String message) {

    public static ErrorResponse from(final Exception e) {
        return new ErrorResponse(e.getMessage());
    }
}
