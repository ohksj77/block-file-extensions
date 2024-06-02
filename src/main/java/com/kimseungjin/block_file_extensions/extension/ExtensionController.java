package com.kimseungjin.block_file_extensions.extension;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService extensionService;

    @GetMapping("/")
    public String fileExtensions(final Model model, @AuthenticationPrincipal final Long memberId) {
        final ExtensionResponse extensionResponse = extensionService.getChosenExtensions(memberId);

        model.addAttribute("fixedExtensions", FixedExtension.getAllValues());
        model.addAttribute("chosenFixedExtensions", extensionResponse.chosenFixedExtensions());
        model.addAttribute("chosenCustomExtensions", extensionResponse.chosenCustomExtensions());

        return "index";
    }

    @PostMapping("api/extensions")
    public String addExtension(
            @RequestBody @Valid final ExtensionRequest extensionRequest,
            @AuthenticationPrincipal final Long memberId) {
        extensionService.updateExtension(extensionRequest, memberId);
        return "redirect:/";
    }
}
