package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management.Files.fileUploadToFolder.FileInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public interface AttachedFilesService {
    AttachedFiles createFiles(long litigationId,String fileCategory,   MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException;
    AttachedFiles getFilesById(long id);
    ResponseEntity<List<FileInfo>> findAllByFileCategory(Pageable pageable, String fileCategory, long id, JwtAuthenticationToken token);
    Page<AttachedFiles> getAllFiles(Pageable pageable, JwtAuthenticationToken token);
    AttachedFiles updateFiles(long id,AttachedFiles attachedFiles, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteFiles(long id, JwtAuthenticationToken token);

}






