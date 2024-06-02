package com.kimseungjin.block_file_extensions.extension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kimseungjin.block_file_extensions.extension.dto.ExtensionRequest;
import com.kimseungjin.block_file_extensions.fixture.ExtensionFixture;
import com.kimseungjin.block_file_extensions.support.service.LoginTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ExtensionServiceTest extends LoginTest {

    @Autowired private ExtensionService extensionService;
    @Autowired private ExtensionRepository extensionRepository;

    @DisplayName("파일 확장자 추가/삭제가 정상 수행되는가")
    @Test
    void addExtension() {
        // given
        final ExtensionRequest request = ExtensionFixture.toAddRequest();

        // when
        extensionService.updateExtension(request, loginUserId());

        // then
        final Extension extension = extensionRepository.findByMemberId(loginUserId()).orElseThrow();
        assertThat(extension.getCustomExtensionValues()).contains(request.name());
    }

    @DisplayName("파일 확장자 삭제가 정상 수행되는가")
    @Test
    void removeExtension() {
        // given
        final ExtensionRequest request = ExtensionFixture.toRemoveRequest();
        extensionService.updateExtension(ExtensionFixture.toAddRequest(), loginUserId());

        // when
        extensionService.updateExtension(request, loginUserId());

        // then
        final Extension extension = extensionRepository.findByMemberId(loginUserId()).orElseThrow();
        assertThat(extension.getCustomExtensionValues()).doesNotContain(request.name());
    }

    @DisplayName("파일 확장자 중복 추가시 예외가 발생하는가")
    @Test
    void addDuplicatedExtension() {
        // given
        final ExtensionRequest request = ExtensionFixture.toAddRequest();
        extensionService.updateExtension(request, loginUserId());

        // when
        // then
        assertThatThrownBy(() -> extensionService.updateExtension(request, loginUserId()))
                .isInstanceOf(RegisteredExtensionException.class);
    }
}
