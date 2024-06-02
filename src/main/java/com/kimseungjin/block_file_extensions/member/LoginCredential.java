package com.kimseungjin.block_file_extensions.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginCredential {

    private static final int MIN_CREDENTIAL_LENGTH = 3;
    private static final int MAX_CREDENTIAL_LENGTH = 10;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    public LoginCredential(final String loginId, final String password) {
        validate(loginId, password);
        this.loginId = loginId;
        this.password = password;
    }

    private void validate(final String loginId, final String password) {
        if (loginId == null || isInvalidLength(loginId) || password == null || password.isBlank()) {
            throw new InvalidLoginCredentialException();
        }
    }

    private boolean isInvalidLength(final String credential) {
        final int length = credential.length();
        return MIN_CREDENTIAL_LENGTH > length || length > MAX_CREDENTIAL_LENGTH;
    }
}
