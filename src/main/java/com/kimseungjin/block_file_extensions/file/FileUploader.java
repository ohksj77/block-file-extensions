package com.kimseungjin.block_file_extensions.file;

import com.kimseungjin.block_file_extensions.file.dto.FileUploadResult;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {

    FileUploadResult upload(final MultipartFile file);

    Resource download(final String filename);
}
