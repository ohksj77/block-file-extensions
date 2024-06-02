package com.kimseungjin.block_file_extensions.extension;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExtensionRequest(@NotBlank String name, @NotNull Boolean isAdded) {}
