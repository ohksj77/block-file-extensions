package com.kimseungjin.block_file_extensions.extension;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/extensions")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService extensionService;

    @PostMapping
    public String updateExtension(
            @RequestBody @Valid final ExtensionRequest extensionRequest,
            @AuthenticationPrincipal final Long memberId) {
        extensionService.updateExtension(extensionRequest, memberId);
        return "redirect:/";
    }
}
