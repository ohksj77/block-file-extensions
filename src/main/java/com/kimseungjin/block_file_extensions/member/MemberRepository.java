package com.kimseungjin.block_file_extensions.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginCredentialLoginId(final String loginId);

    boolean existsByLoginCredentialLoginId(final String loginId);
}
