package com.kimseungjin.block_file_extensions.fixture;

import com.kimseungjin.block_file_extensions.member.Member;
import com.kimseungjin.block_file_extensions.member.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberFixture {
    LOGIN_MEMBER("logined", "$2a$10$H72/TQEEhkVs1RLg03wl9OT31HSVGcYMs/ftn2u6oRS4iwJCRfZHu"),
    MEMBER1("member1", "$2a$10$H72/TQEEhkVs1RLg03wl9OT31HSVGcYMs/ftn2u6oRS4iwJCRfZHu"),
    MEMBER2("member2", "$2a$10$T9EPJaXl7UCxp2bFm5w.6edudus2e6LVu/DD1R6qeS.c7q7wHOjHu"),
    REGISTER_REQUEST("register", "password");

    private final String loginId;
    private final String password;

    public Member toEntity() {
        return new Member(loginId, password);
    }

    public RegisterRequest toRegisterRequest() {
        return new RegisterRequest(loginId, password);
    }
}
