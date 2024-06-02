package com.kimseungjin.block_file_extensions.global.advice;

import com.kimseungjin.block_file_extensions.global.exception.EntityNotFoundException;
import com.kimseungjin.block_file_extensions.global.security.LoginException;

import jakarta.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(
            final EntityNotFoundException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(LoginException.class)
    public String handleLoginException(
            final LoginException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(
            final AuthenticationException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(
            final ValidationException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(
            final NullPointerException e, final RedirectAttributes redirectAttributes) {
        return handle(redirectAttributes, e.getMessage());
    }

    private String handle(final RedirectAttributes redirectAttributes, final String errorMessage) {
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        return "redirect:/error";
    }
}
