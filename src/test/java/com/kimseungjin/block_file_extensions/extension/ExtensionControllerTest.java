package com.kimseungjin.block_file_extensions.extension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        doNothing().when(extensionService).updateExtension(any(), any());
        final ExtensionRequest request = ExtensionFixture.toAddRequest();

        final ResultActions perform =
                mockMvc.perform(
                        post("/api/extensions")
                                .accept(MediaType.parseMediaType("application/html;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toRequestBody(request)));

        perform.andExpect(status().is3xxRedirection());
    }
}
