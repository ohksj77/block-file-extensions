package com.kimseungjin.block_file_extensions.extension;

public class InvalidExtensionException extends IllegalArgumentException {

    private static final String MESSAGE = "유효하지 않은 확장자입니다.";

    public InvalidExtensionException() {
        super(MESSAGE);
    }
}
