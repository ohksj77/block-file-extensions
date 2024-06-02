package com.kimseungjin.block_file_extensions.file;

public class FileOwnerMismatchException extends IllegalArgumentException {

    private static final String MESSAGE = "파일 소유자가 일치하지 않습니다.";

    public FileOwnerMismatchException() {
        super(MESSAGE);
    }
}
