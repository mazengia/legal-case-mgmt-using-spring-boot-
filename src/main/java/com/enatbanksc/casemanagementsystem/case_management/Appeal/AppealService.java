package com.enatbanksc.casemanagementsystem.case_management.Appeal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;



@Service
public interface AppealService {
    Appeal createAppeal(Appeal appeal, JwtAuthenticationToken token) throws IllegalAccessException;
    Appeal getAppealById(long id);
    Page<Appeal> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorneyHandlingTheCase,JwtAuthenticationToken token);

    Page<Appeal> findAllByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token);

    Page<Appeal> getAllAppeal(Pageable pageable, JwtAuthenticationToken token);
    Appeal updateAppeal(long id, Appeal appeal, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteAppeal(long id, JwtAuthenticationToken token);

}






