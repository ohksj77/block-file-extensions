package com.kimseungjin.block_file_extensions.extension;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExtensionService {

    private final ExtensionRepository extensionRepository;

    @Transactional
    public void updateExtension(final ExtensionRequest extensionRequest, final Long memberId) {
        final Extension extension = findByMemberId(memberId);
        extension.updateExtension(extensionRequest.name(), extensionRequest.isAdded());
    }

    @Transactional(readOnly = true)
    public ExtensionResponse getChosenExtensions(final Long memberId) {
        final Extension extension = findByMemberId(memberId);
        return new ExtensionResponse(
                extension.getFixedExtensionValues(), extension.getCustomExtensionValues());
    }

    private Extension findByMemberId(final Long memberId) {
        return extensionRepository
                .findByMemberId(memberId)
                .orElseGet(() -> extensionRepository.save(new Extension(memberId)));
    }
}
