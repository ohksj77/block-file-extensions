package com.kimseungjin.block_file_extensions.member.dto;

import org.hibernate.validator.constraints.Length;

public record RegisterRequest(
        @Length(min = 3, max = 10) String loginId, @Length(min = 3, max = 10) String password) {}
