package com.kimseungjin.block_file_extensions.extension;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {
    Optional<Extension> findByMemberId(final Long memberId);
}
