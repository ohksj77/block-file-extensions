package com.kimseungjin.block_file_extensions.global.exception;

public class EntityNotFoundException extends IllegalArgumentException {

    private static final String MESSAGE = "해당하는 엔티티가 존재하지 않습니다.";

    public EntityNotFoundException() {
        super(MESSAGE);
    }
}
