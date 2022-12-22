package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Branch;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface LitigationService {
    Litigation createLitigation(Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException;
    Litigation getLitigation(long id);
    Page<Litigation> getLitigations(Pageable pageable);
    Litigation updateLitigation(long id, Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException;

     void deleteLitigation(long id, JwtAuthenticationToken token);
    Page<Litigation> getLitigationByCaseStage(Pageable pageable, CaseStage caseStage, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByStatus(Pageable pageable, String status, JwtAuthenticationToken token);

    Page<Litigation> findLitigationByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token);

}
