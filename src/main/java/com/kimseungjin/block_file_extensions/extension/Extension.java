package com.kimseungjin.block_file_extensions.extension;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Extension {

    private static final Pattern EXTENSION_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]{2,5}$");

    @Id @GeneratedValue private Long id;

    @Column(nullable = false, unique = true)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<FixedExtension> fixedExtensions = new HashSet<>();

    @ElementCollection private Set<String> customExtensions = new LinkedHashSet<>();

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
            throw new DuplicateExtensionException();
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
}
