package com.kimseungjin.block_file_extensions.extension;

import static com.kimseungjin.block_file_extensions.support.docs.ApiDocsUtils.getDocumentRequest;
import static com.kimseungjin.block_file_extensions.support.docs.ApiDocsUtils.getDocumentResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kimseungjin.block_file_extensions.extension.dto.ExtensionRequest;
import com.kimseungjin.block_file_extensions.fixture.ExtensionFixture;
import com.kimseungjin.block_file_extensions.support.controller.ControllerTest;
import com.kimseungjin.block_file_extensions.support.web.UserIdArgumentResolverImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@Import(UserIdArgumentResolverImpl.class)
@WebMvcTest(
        controllers = ExtensionController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class ExtensionControllerTest extends ControllerTest {

    @MockBean private ExtensionService extensionService;

    @DisplayName("파일 확장자 추가/삭제가 정상 작동하는가")
    @Test
    void updateExtension() throws Exception {
        // given
        doNothing().when(extensionService).updateExtension(any(), any());
        final ExtensionRequest request = ExtensionFixture.toAddRequest();

        // when
        final ResultActions perform =
                mockMvc.perform(
                        post("/api/extensions")
                                .accept(MediaType.parseMediaType("application/html;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toRequestBody(request)));

        // then
        perform.andExpect(status().is3xxRedirection());

        // docs
        perform.andDo(print())
                .andDo(document("post extension", getDocumentRequest(), getDocumentResponse()));
    }
}
