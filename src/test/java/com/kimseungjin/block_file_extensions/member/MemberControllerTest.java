package com.kimseungjin.block_file_extensions.member;

import static com.kimseungjin.block_file_extensions.support.docs.ApiDocsUtils.getDocumentRequest;
import static com.kimseungjin.block_file_extensions.support.docs.ApiDocsUtils.getDocumentResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kimseungjin.block_file_extensions.fixture.MemberFixture;
import com.kimseungjin.block_file_extensions.member.dto.RegisterRequest;
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
        controllers = MemberController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class MemberControllerTest extends ControllerTest {

    @MockBean private MemberService memberService;

    @DisplayName("회원 가입이 정상 수행되는가")
    @Test
    void register() throws Exception {
        // given
        doNothing().when(memberService).register(any());
        final RegisterRequest request = MemberFixture.REGISTER_REQUEST.toRegisterRequest();

        // when
        final ResultActions perform =
                mockMvc.perform(
                        post("/api/members/register")
                                .accept(MediaType.parseMediaType("application/html;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toRequestBody(request)));

        // then
        perform.andExpect(status().is3xxRedirection());

        // docs
        perform.andDo(print())
                .andDo(document("post register", getDocumentRequest(), getDocumentResponse()));
    }
}
