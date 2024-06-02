package com.kimseungjin.block_file_extensions.extension;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExtensionControllerAdvice {

    @ExceptionHandler(DuplicateExtensionException.class)
    public String handleDuplicateExtensionException(
            final DuplicateExtensionException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }
}
