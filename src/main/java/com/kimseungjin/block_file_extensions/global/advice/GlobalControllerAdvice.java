package com.kimseungjin.block_file_extensions.global.advice;

import com.kimseungjin.block_file_extensions.global.exception.EntityNotFoundException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse handleEntityNotFoundException(final EntityNotFoundException e) {
        return ErrorResponse.from(e);
    }
}
