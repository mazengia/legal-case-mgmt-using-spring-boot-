package com.enatbanksc.casemanagementsystem.case_management.Executions;

import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface ExecutionsService {
    Executions createLitigation(Executions executions, JwtAuthenticationToken token) throws IllegalAccessException;
    Executions getLitigation(long id);
    Page<Executions> getLitigations(Pageable pageable);
    Executions updateLitigation(long id, Executions executions, JwtAuthenticationToken token) throws IllegalAccessException;

     void deleteLitigation(long id, JwtAuthenticationToken token);
    Page<Executions> getLitigationByCaseStage(Pageable pageable, CaseStage caseStage, JwtAuthenticationToken token);

    Page<Executions> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<Executions> findLitigationByStatus(Pageable pageable, String status, JwtAuthenticationToken token);

    Page<Executions> findLitigationByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token);

    Page<Executions> findLitigationByFilter(Pageable pageable, String filterValue , JwtAuthenticationToken token);

    Page<Executions> findLitigationByFilterByStatus(Pageable pageable, String filterValue, String status, JwtAuthenticationToken token);

    Page<Executions> findLitigationByFilterByattorney(Pageable pageable, String filterValue, String attorney, JwtAuthenticationToken token);

    Page<Executions> findLitigationByFilterByBranch(Pageable pageable, String filterValue, Long branchId, JwtAuthenticationToken token);
}
