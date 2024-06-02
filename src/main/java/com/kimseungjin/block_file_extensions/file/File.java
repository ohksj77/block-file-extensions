package com.kimseungjin.block_file_extensions.file;

import com.kimseungjin.block_file_extensions.global.audit.Auditable;
import com.kimseungjin.block_file_extensions.global.audit.BaseTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SoftDelete;

@Entity
@SoftDelete
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File implements Auditable {

    @Id @GeneratedValue private Long id;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Long memberId;

    @Getter @Setter @Embedded private BaseTime baseTime;

    public File(final String name, final String url, final Long memberId) {
        this.name = name;
        this.url = url;
        this.memberId = memberId;
    }

    public void validateFileOwner(final Long memberId) {
        if (!this.memberId.equals(memberId)) {
            throw new FileOwnerMismatchException();
        }
    }
}
