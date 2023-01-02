package com.enatbanksc.casemanagementsystem.case_management.Files;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;



@Service
public interface AttachedFilesService {
    AttachedFiles createFiles(AttachedFiles attachedFiles, JwtAuthenticationToken token) throws IllegalAccessException;
    AttachedFiles getFilesById(long id);
    Page<AttachedFiles> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorneyHandlingTheCase, JwtAuthenticationToken token);
    Page<AttachedFiles> getAllFiles(Pageable pageable, JwtAuthenticationToken token);
    AttachedFiles updateFiles(long id, AttachedFiles attachedFiles, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteFiles(long id, JwtAuthenticationToken token);

}






