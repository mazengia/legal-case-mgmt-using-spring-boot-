package com.enatbanksc.casemanagementsystem.case_management.Files;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface FilesService {
    Files createFiles(long litigationId, String fileCategory, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException;

     void getFilesById(long id);
    Page<Files> findAllExecutionFilesByExecutionId(Pageable pageable, String fileCategory, long id, JwtAuthenticationToken token);

    Page<Files> getAllFiles(Pageable pageable, JwtAuthenticationToken token);

    Files updateFiles(long id, Files files, MultipartFile file, JwtAuthenticationToken token) throws IllegalAccessException;

         void deleteFiles(String fileName);
    void deleteFilesById(long id);
}






