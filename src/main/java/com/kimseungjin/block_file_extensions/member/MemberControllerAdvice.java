package com.kimseungjin.block_file_extensions.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class MemberControllerAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateLoginIdException.class)
    public String handleDuplicateLoginIdException(
            final DuplicateLoginIdException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidLoginCredentialException.class)
    public String handleInvalidLoginCredentialException(
            final InvalidLoginCredentialException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    private String handle(final RedirectAttributes redirectAttributes, final String errorMessage) {
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        return "redirect:/error";
    }
}
