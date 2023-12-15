package com.herman87.uploadeimage.service;

import com.herman87.uploadeimage.domain.FileData;
import com.herman87.uploadeimage.repository.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    private final String FOLDER_PATH = "C:\\H87\\uploade-image\\src\\main\\resources\\static\\images";

    @Transactional
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        fileRepository.save(
                FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
                        .build()
        );
        file.transferTo(new File(filePath));
        return "file uploaded successfully: " + filePath;
    }

    @Transactional
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;

    }
}
