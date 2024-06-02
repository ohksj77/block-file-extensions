package com.kimseungjin.block_file_extensions.file;

import com.kimseungjin.block_file_extensions.extension.Extension;
import com.kimseungjin.block_file_extensions.extension.ExtensionService;
import com.kimseungjin.block_file_extensions.global.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FileUploader fileUploader;
    private final ExtensionService extensionService;

    public void upload(final MultipartFile file, final Long memberId) {
        final Extension extension = extensionService.findByMemberId(memberId);
        final String filename = file.getOriginalFilename();
        extension.validateExtension(filename);

        final FileUploadResult fileUploadResult = fileUploader.upload(file);
        fileRepository.save(
                new File(fileUploadResult.filename(), fileUploadResult.uploadUrl(), memberId));
    }

    public Resource download(final String filename, final Long memberId) {
        final File file = getEntityByName(filename);
        file.validateFileOwner(memberId);
        return fileUploader.download(file.getName());
    }

    private File getEntityByName(final String filename) {
        return fileRepository.findByName(filename).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<String> getFilenames(final Long memberId) {
        return fileRepository.findByMemberId(memberId).stream().map(File::getName).toList();
    }
}
