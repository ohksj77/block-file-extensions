package com.kimseungjin.block_file_extensions.member;

import com.kimseungjin.block_file_extensions.member.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(final RegisterRequest registerRequest) {
        final String loginId = registerRequest.loginId();
        validateLoginId(loginId);

        final String encodedPassword = passwordEncoder.encode(registerRequest.password());
        final Member member = new Member(loginId, encodedPassword);

        memberRepository.save(member);
    }

    private void validateLoginId(final String loginId) {
        if (memberRepository.existsByLoginCredentialLoginId(loginId)) {
            throw new DuplicateLoginIdException();
        }
    }
}
