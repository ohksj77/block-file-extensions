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
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
@SoftDelete
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Extension implements Auditable {

    private static final Pattern EXTENSION_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]{2,5}$");
    private static final char FILE_EXTENSION_DELIMITER = '.';

    @Id @GeneratedValue private Long id;

    @Column(nullable = false, unique = true)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<FixedExtension> fixedExtensions = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> customExtensions = new LinkedHashSet<>();

    @Getter @Setter @Embedded private BaseTime baseTime;

    public Extension(final Long memberId) {
        this.memberId = memberId;
    }

    public void updateExtension(final String extension, final Boolean isAdded) {
        if (FixedExtension.contains(extension)) {
            updateFixedExtension(extension, isAdded);
            return;
        }
        updateCustomExtension(extension, isAdded);
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
        if (customExtensions.contains(extension) || isInvalidExtensionPattern(extension)) {
            throw new RegisteredExtensionException();
        }
    }

    private boolean isInvalidExtensionPattern(final String extension) {
        return !EXTENSION_PATTERN.matcher(extension).matches();
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
                && customExtensions.contains(extension);
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
