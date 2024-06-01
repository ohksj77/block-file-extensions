package com.kimseungjin.block_file_extensions.member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {

    @DisplayName("로그인 정보의 길이가 3자 미만인 경우 예외가 발생하는가")
    @Test
    void loginCredentialLessThan3() {
        // given
        final String loginId = "12";

        // when
        // then
        assertThatThrownBy(() -> new Member(loginId, "password"))
                .isInstanceOf(InvalidLoginCredentialException.class);
    }

    @DisplayName("로그인 정보의 길이가 10자 초과인 경우 예외가 발생하는가")
    @Test
    void loginCredentialGreaterThan10() {
        // given
        final String loginId = "1234567891011";

        // when
        // then
        assertThatThrownBy(() -> new Member(loginId, "password"))
                .isInstanceOf(InvalidLoginCredentialException.class);
    }

    @DisplayName("로그인 정보의 길이가 3자 이상 10자 이하인 경우 정상 생성되는가")
    @Test
    void loginCredentialBetween3And10() {
        // given
        final String loginId = "123456";
        final String password = "1234";

        // when
        final Member member = new Member(loginId, password);

        // then
        assertThat(member.getLoginId()).isEqualTo(loginId);
        assertThat(member.getPassword()).isEqualTo(password);
    }
}
