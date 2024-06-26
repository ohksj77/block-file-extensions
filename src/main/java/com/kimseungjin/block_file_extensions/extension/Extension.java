package com.kimseungjin.block_file_extensions.extension;

import com.kimseungjin.block_file_extensions.global.audit.Auditable;
import com.kimseungjin.block_file_extensions.global.audit.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@SoftDelete
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Extension implements Auditable {

    private static final char FILE_EXTENSION_DELIMITER = '.';
    private static final int MAX_EXTENSION_LENGTH = 20;
    private static final int MAX_CUSTOM_EXTENSION_SIZE = 200;

    @Id @GeneratedValue private Long id;

    @Column(nullable = false, unique = true)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<FixedExtension> fixedExtensions = new HashSet<>(FixedExtension.values().length);

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> customExtensions = new LinkedHashSet<>(MAX_CUSTOM_EXTENSION_SIZE);

    @Getter @Setter @Embedded private BaseTime baseTime;

    public Extension(final Long memberId) {
        this.memberId = memberId;
    }

    public void updateExtension(final String extension, final Boolean isAdded) {
        validateLength(extension);
        if (FixedExtension.contains(extension)) {
            updateFixedExtension(extension, isAdded);
            return;
        }
        updateCustomExtension(extension, isAdded);
    }

    private void validateLength(final String extension) {
        if (extension.length() > MAX_EXTENSION_LENGTH) {
            throw new InvalidExtensionLengthException();
        }
    }

    private void updateCustomExtension(final String extension, final Boolean isAdded) {
        if (isAdded) {
            validateCustomExtension(extension);
            customExtensions.add(extension);
            return;
        }
        customExtensions.remove(extension);
    }

    private void updateFixedExtension(final String extension, final Boolean isAdded) {
        if (isAdded) {
            fixedExtensions.add(FixedExtension.valueOfLowerCase(extension));
            return;
        }
        System.out.println(fixedExtensions.size());
        fixedExtensions.remove(FixedExtension.valueOfLowerCase(extension));
        System.out.println(fixedExtensions.size());
    }

    private void validateCustomExtension(final String extension) {
        if (customExtensions.contains(extension)) {
            throw new RegisteredExtensionException();
        }
    }

    public Set<String> getFixedExtensionValues() {
        return fixedExtensions.stream()
                .map(FixedExtension::toString)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<String> getCustomExtensionValues() {
        return new LinkedHashSet<>(customExtensions);
    }

    private boolean isInvalidExtension(final String extension) {
        return FixedExtension.findExtension(extension).map(fixedExtensions::contains).orElse(false)
                || customExtensions.contains(extension);
    }

    public void validateExtension(final String originalFilename) {
        final String extension = extractExtension(originalFilename);
        if (isInvalidExtension(extension)) {
            throw new RegisteredExtensionException();
        }
    }

    private String extractExtension(final String originalFilename) {
        final int lastIndexOfDot = originalFilename.lastIndexOf(FILE_EXTENSION_DELIMITER);
        return originalFilename.substring(lastIndexOfDot + 1);
    }
}
