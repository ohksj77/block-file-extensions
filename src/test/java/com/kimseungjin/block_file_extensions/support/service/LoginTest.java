package com.kimseungjin.block_file_extensions.support.service;

import com.kimseungjin.block_file_extensions.fixture.MemberFixture;
import com.kimseungjin.block_file_extensions.member.Member;
import com.kimseungjin.block_file_extensions.member.MemberRepository;
import com.kimseungjin.block_file_extensions.support.database.DatabaseSweepTest;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

@DatabaseTest
@DatabaseSweepTest
public abstract class LoginTest {

    @Autowired protected MemberRepository memberRepository;
    protected Member loginUser;

    @BeforeEach
    void setup() {
        final Member member = MemberFixture.LOGIN_MEMBER.toEntity();
        loginUser = memberRepository.save(member);
    }

    protected Long loginUserId() {
        return loginUser.getId();
    }
}
