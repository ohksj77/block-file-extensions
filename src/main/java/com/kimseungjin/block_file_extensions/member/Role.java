package com.kimseungjin.block_file_extensions.member;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String toString() {
        return name().replace("ROLE_", "");
    }
}
