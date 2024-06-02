package com.kimseungjin.block_file_extensions.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileLocalUploader implements FileUploader {

    private static final String FILENAME_FORMAT = "%s_%s";
    private final Path fileStorageLocation;

    public FileLocalUploader(@Value("${spring.servlet.multipart.location}") final String location) {
        this.fileStorageLocation = Paths.get(location).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException();
        }
    }

    @Override
    public FileUploadResult upload(final MultipartFile file) {
        final String filename =
                String.format(
                        FILENAME_FORMAT,
                        UUID.randomUUID(),
                        Objects.requireNonNull(file.getOriginalFilename()));
        storeFile(file, filename);
        try {
            final String uploadUrl =
                    ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/files/" + filename)
                            .toUriString();
            return new FileUploadResult(uploadUrl, filename);
        } catch (final Exception e) {
            throw new FileStorageException();
        }
    }

    private void storeFile(final MultipartFile file, final String filename) {
        try (final InputStream inputStream = file.getInputStream()) {
            final Path targetLocation = this.fileStorageLocation.resolve(filename);

            Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException e) {
            throw new FileStorageException();
        }
    }

    @Override
    public Resource download(final String filename) {
        try {
            final Path path =
                    Paths.get(this.fileStorageLocation.resolve(filename).normalize().toString());
            final byte[] bytes = Files.readAllBytes(path);

            return new ByteArrayResource(bytes);
        } catch (final IOException e) {
            throw new FileStorageException();
        }
    }
}
