package com.kimseungjin.block_file_extensions.fixture;

import com.kimseungjin.block_file_extensions.extension.Extension;
import com.kimseungjin.block_file_extensions.extension.ExtensionRequest;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public enum ExtensionFixture {
    MEMBER_ADDED_EXTENSIONS(1L, List.of("jpeg", "png")),
    MEMBER_WITH_NO_EXTENSIONS(2L, List.of());

    private final Long memberId;
    private final List<String> addedExtensions;

    public static ExtensionRequest toRemoveRequest() {
        return new ExtensionRequest("jpeg", false);
    }

    public static ExtensionRequest toAddRequest() {
        return new ExtensionRequest("jpeg", true);
    }

    public Extension toEntity() {
        final Extension extension = new Extension(memberId);
        addedExtensions.forEach(element -> extension.updateExtension(element, true));
        return extension;
    }
}
