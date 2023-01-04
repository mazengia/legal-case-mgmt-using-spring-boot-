package com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder;

import com.enatbanksc.casemanagementsystem.case_management.Files.AttachedFiles;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
public interface FilesStorageService {
    public void init();
    public AttachedFiles save(MultipartFile file);
    public Resource load(String filename);
    public List<Resource> loadFile(String filename);
    public void deleteAll();
    public void deleteByFileName(String filename);
    public Stream<Path> loadAll();
}