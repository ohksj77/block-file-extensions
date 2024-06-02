package com.kimseungjin.block_file_extensions.extension;

public class RegisteredExtensionException extends IllegalArgumentException {

    private static final String MESSAGE = "이미 등록된 확장자입니다.";

    public RegisteredExtensionException() {
        super(MESSAGE);
    }
}
