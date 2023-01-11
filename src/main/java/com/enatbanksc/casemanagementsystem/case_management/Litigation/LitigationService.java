package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface LitigationService {
//    ,Litigation litigation
    Litigation createLitigation(Litigation litigation , JwtAuthenticationToken token) throws IllegalAccessException;
    Litigation getLitigationById(long id);
    Page<Litigation> getAllLitigation(Pageable pageable);
    Litigation updateLitigation(long id, Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException;

     void deleteLitigation(long id, JwtAuthenticationToken token);
    Page<Litigation> getLitigationByCaseStage(Pageable pageable, CaseStage caseStage, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByStatus(Pageable pageable, String status, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token);

    Page<Litigation> findAllByBranchIdIsNotContaining(Pageable pageable, long branchId, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByFilter(Pageable pageable, String filterValue , JwtAuthenticationToken token);

    Page<Litigation> findLitigationByFilterByStatus(Pageable pageable, String filterValue, String status, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByFilterByattorney(Pageable pageable, String filterValue, String attorney, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByFilterByBranch(Pageable pageable, String filterValue, Long branchId, JwtAuthenticationToken token);
}
