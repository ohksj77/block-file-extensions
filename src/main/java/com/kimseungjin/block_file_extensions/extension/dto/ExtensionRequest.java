package com.kimseungjin.block_file_extensions.extension.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ExtensionRequest(
        @Pattern(regexp = "^[a-zA-Z0-9_-]{2,5}$") String name, @NotNull Boolean isAdded) {}
