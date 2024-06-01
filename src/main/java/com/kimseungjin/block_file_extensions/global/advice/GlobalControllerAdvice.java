package com.kimseungjin.block_file_extensions.global.advice;

import com.kimseungjin.block_file_extensions.global.exception.EntityNotFoundException;
import com.kimseungjin.block_file_extensions.global.exception.LoginFailedException;

import jakarta.validation.ValidationException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(
            final EntityNotFoundException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(LoginFailedException.class)
    public String handleLoginFailedException(
            final IllegalArgumentException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(
            final AuthenticationException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(
            final ValidationException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(
            final NullPointerException e, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/error";
    }
}
