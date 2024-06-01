package com.kimseungjin.block_file_extensions.member;

public class InvalidLoginCredentialException extends IllegalArgumentException {

    private static final String MESSAGE = "로그인 정보가 잘못되었습니다.";

    public InvalidLoginCredentialException() {
        super(MESSAGE);
    }
}
