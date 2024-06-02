package com.kimseungjin.block_file_extensions.file;

public class FileStorageException extends IllegalStateException {

    private static final String MESSAGE = "파일 저장 작업에 실패했습니다.";

    public FileStorageException() {
        super(MESSAGE);
    }
}
