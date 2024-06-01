package com.kimseungjin.block_file_extensions.member;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String loginId, @NotBlank String password) {}
