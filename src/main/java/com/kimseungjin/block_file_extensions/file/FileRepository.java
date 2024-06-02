package com.kimseungjin.block_file_extensions.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByMemberId(final Long memberId);

    Optional<File> findByName(final String name);
}
