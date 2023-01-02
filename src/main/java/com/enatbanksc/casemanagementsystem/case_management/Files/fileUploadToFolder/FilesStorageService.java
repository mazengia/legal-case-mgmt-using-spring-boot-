package com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
public interface FilesStorageService {
    public void init();
    public Object save(MultipartFile file);
    public Resource load(String filename);
    public void deleteAll();
    public void deleteByFileName(String filename);
    public Stream<Path> loadAll();
}