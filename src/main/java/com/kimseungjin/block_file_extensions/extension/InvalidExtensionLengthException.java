package com.kimseungjin.block_file_extensions.extension;

public class InvalidExtensionLengthException extends IllegalArgumentException {

    private static final String MESSAGE = "파일 확장자의 최대 길이를 초과했습니다.";

    public InvalidExtensionLengthException() {
        super(MESSAGE);
    }
}
