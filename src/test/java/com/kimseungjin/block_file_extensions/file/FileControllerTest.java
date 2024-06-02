package com.kimseungjin.block_file_extensions.file;

import static com.kimseungjin.block_file_extensions.support.docs.ApiDocsUtils.getDocumentRequest;
import static com.kimseungjin.block_file_extensions.support.docs.ApiDocsUtils.getDocumentResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kimseungjin.block_file_extensions.support.controller.ControllerTest;
import com.kimseungjin.block_file_extensions.support.web.UserIdArgumentResolverImpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.io.ByteArrayInputStream;

@Import(UserIdArgumentResolverImpl.class)
@WebMvcTest(FileController.class)
class FileControllerTest extends ControllerTest {

    @MockBean private FileService fileService;

    @DisplayName("파일 업로드가 수행되는가")
    @Test
    void uploadFile() throws Exception {
        // given
        doNothing().when(fileService).upload(any(), any());

        // when
        final ResultActions perform =
                mockMvc.perform(
                        post("/api/files")
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .content(toRequestBody("file을 request시 넣어주세요")));

        // then
        perform.andExpect(status().is3xxRedirection());

        // docs
        perform.andDo(print())
                .andDo(document("post file", getDocumentRequest(), getDocumentResponse()));
    }

    @DisplayName("파일 다운로드가 수행되는가")
    @Test
    void downloadFile() throws Exception {
        // given
        when(fileService.download(any(), any()))
                .thenReturn(new InputStreamResource(new ByteArrayInputStream(new byte[] {})));

        // when
        final ResultActions perform = mockMvc.perform(get("/api/files?filename=file.txt"));

        // then
        perform.andExpect(status().isOk());

        // docs
        perform.andDo(print())
                .andDo(document("get file", getDocumentRequest(), getDocumentResponse()));
    }
}
