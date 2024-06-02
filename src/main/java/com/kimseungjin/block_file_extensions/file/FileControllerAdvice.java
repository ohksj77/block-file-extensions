package com.kimseungjin.block_file_extensions.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileControllerAdvice {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(FileOwnerMismatchException.class)
    public String handleFileOwnerMismatchException(
            final FileOwnerMismatchException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileStorageException.class)
    public String handleFileStorageException(
            final FileStorageException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    private String handle(final RedirectAttributes redirectAttributes, final String errorMessage) {
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        return "redirect:/error";
    }
}
