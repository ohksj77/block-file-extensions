package com.kimseungjin.block_file_extensions.global.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginException extends UsernameNotFoundException {

    private static final String MESSAGE = "로그인에 실패했습니다. 다시 시도해주세요.";

    public LoginException() {
        super(MESSAGE);
    }
}
