package com.kimseungjin.block_file_extensions.member;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kimseungjin.block_file_extensions.fixture.MemberFixture;
import com.kimseungjin.block_file_extensions.support.controller.ControllerTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(
        controllers = MemberController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class MemberControllerTest extends ControllerTest {

    @MockBean private MemberService memberService;

    @DisplayName("회원 가입이 정상 수행되는가")
    @Test
    void register() throws Exception {
        doNothing().when(memberService).register(any());
        final RegisterRequest request = MemberFixture.MEMBER1.toRegisterRequest();

        final ResultActions perform =
                mockMvc.perform(
                        post("/api/members/register")
                                .accept(MediaType.parseMediaType("application/html;charset=UTF-8"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toRequestBody(request)));

        perform.andExpect(status().is3xxRedirection());
    }
}
