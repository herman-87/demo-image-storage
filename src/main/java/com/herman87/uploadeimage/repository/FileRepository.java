package com.herman87.uploadeimage.repository;

import com.herman87.uploadeimage.domain.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String fileName);
}
