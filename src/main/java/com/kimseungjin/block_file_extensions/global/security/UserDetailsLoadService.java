package com.kimseungjin.block_file_extensions.global.security;

import com.kimseungjin.block_file_extensions.member.Member;
import com.kimseungjin.block_file_extensions.member.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsLoadService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Member member =
                memberRepository
                        .findByLoginCredentialLoginId(username)
                        .orElseThrow(LoginException::new);

        return User.withUsername(String.valueOf(member.getId()))
                .password(member.getLoginCredential().getPassword())
                .roles(member.getRoleValues())
                .build();
    }
}
