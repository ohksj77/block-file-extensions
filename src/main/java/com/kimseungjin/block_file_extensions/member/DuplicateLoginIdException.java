package com.kimseungjin.block_file_extensions.member;

public class DuplicateLoginIdException extends IllegalArgumentException {

    private static final String MESSAGE = "이미 사용중인 아이디입니다.";

    public DuplicateLoginIdException() {
        super(MESSAGE);
    }
}
