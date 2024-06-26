package com.kimseungjin.block_file_extensions.extension;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExtensionControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RegisteredExtensionException.class)
    public String handleRegisteredExtensionException(
            final RegisteredExtensionException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidExtensionException.class)
    public String handleInvalidExtensionException(
            final InvalidExtensionException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    private String handle(final RedirectAttributes redirectAttributes, final String errorMessage) {
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        return "redirect:/error";
    }
}
