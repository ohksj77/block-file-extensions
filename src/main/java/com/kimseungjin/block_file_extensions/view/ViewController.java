package com.kimseungjin.block_file_extensions.view;

import com.kimseungjin.block_file_extensions.extension.ExtensionResponse;
import com.kimseungjin.block_file_extensions.extension.ExtensionService;
import com.kimseungjin.block_file_extensions.extension.FixedExtension;
import com.kimseungjin.block_file_extensions.file.FileService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {

    private final ExtensionService extensionService;
    private final FileService fileService;

    @GetMapping("/")
    public String fileExtensions(final Model model, @AuthenticationPrincipal final Long memberId) {
        final ExtensionResponse extensionResponse = extensionService.getChosenExtensions(memberId);
        final List<String> filenames = fileService.getFilenames(memberId);

        model.addAttribute("fixedExtensions", FixedExtension.getAllValues());
        model.addAttribute("chosenFixedExtensions", extensionResponse.chosenFixedExtensions());
        model.addAttribute("chosenCustomExtensions", extensionResponse.chosenCustomExtensions());
        model.addAttribute("filenames", filenames);

        return "index";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @GetMapping("login")
    public String login() {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("error")
    public String error(final Model model) {
        return "error";
    }
}
