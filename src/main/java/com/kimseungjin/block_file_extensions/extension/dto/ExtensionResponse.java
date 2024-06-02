package com.kimseungjin.block_file_extensions.extension.dto;

import java.util.Set;

public record ExtensionResponse(
        Set<String> chosenFixedExtensions, Set<String> chosenCustomExtensions) {}
