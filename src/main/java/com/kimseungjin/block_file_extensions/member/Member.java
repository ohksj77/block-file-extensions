package com.kimseungjin.block_file_extensions.member;

import com.kimseungjin.block_file_extensions.global.audit.AuditListener;
import com.kimseungjin.block_file_extensions.global.audit.Auditable;
import com.kimseungjin.block_file_extensions.global.audit.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SoftDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@SoftDelete
@EntityListeners(AuditListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements Auditable {

    private static final int MIN_CREDENTIAL_LENGTH = 3;
    private static final int MAX_CREDENTIAL_LENGTH = 10;

    @Id @GeneratedValue private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> role = new ArrayList<>(List.of(Role.ROLE_USER));

    @Setter @Embedded private BaseTime baseTime;

    public Member(final String loginId, final String password) {
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

    public String[] getRoleValues() {
        return this.role.stream().map(Role::toString).toArray(String[]::new);
    }
}
