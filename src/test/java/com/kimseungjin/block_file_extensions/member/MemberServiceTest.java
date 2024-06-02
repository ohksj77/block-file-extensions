package com.kimseungjin.block_file_extensions.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kimseungjin.block_file_extensions.fixture.MemberFixture;
import com.kimseungjin.block_file_extensions.member.dto.RegisterRequest;
import com.kimseungjin.block_file_extensions.support.service.LoginTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MemberServiceTest extends LoginTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;

    @DisplayName("회원 가입이 정상 수행되는가")
    @Test
    void register() {
        // given
        final RegisterRequest request = MemberFixture.MEMBER1.toRegisterRequest();

        // when
        memberService.register(request);

        // then
        final String loginId = request.loginId();
        final Member member = memberRepository.findByLoginId(loginId).orElseThrow();
        assertThat(member.getLoginId()).isEqualTo(loginId);
    }

    @DisplayName("회원 가입시 중복된 아이디가 존재할 경우 예외가 발생하는가")
    @Test
    void registerWithDuplicatedLoginId() {
        // given
        final RegisterRequest request = MemberFixture.MEMBER1.toRegisterRequest();
        memberService.register(request);

        // when
        // then
        assertThatThrownBy(() -> memberService.register(request))
                .isInstanceOf(DuplicateLoginIdException.class);
    }
}
