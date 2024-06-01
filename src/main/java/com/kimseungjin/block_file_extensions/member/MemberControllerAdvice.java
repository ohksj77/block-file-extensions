package com.kimseungjin.block_file_extensions.member;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(DuplicateLoginIdException.class)
    public String handleDuplicateLoginIdException(
            final DuplicateLoginIdException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(InvalidLoginCredentialException.class)
    public String handleInvalidLoginCredentialException(
            final InvalidLoginCredentialException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }
}
