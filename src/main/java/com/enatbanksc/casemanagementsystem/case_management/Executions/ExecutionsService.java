package com.enatbanksc.casemanagementsystem.case_management.Executions;

import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface ExecutionsService {
    Executions createExecutions(Executions executions, JwtAuthenticationToken token) throws IllegalAccessException;
    Executions getExecutionsById(long id);
    Page<Executions> getExecutions(Pageable pageable);
    Executions updateExecutions(long id, Executions executions, JwtAuthenticationToken token) throws IllegalAccessException;

     void deleteExecutions(long id);
    Page<Executions> getExecutionsByCaseStage(Pageable pageable, CaseStage caseStage, JwtAuthenticationToken token);

    Page<Executions> findExecutionsByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<Executions> findExecutionsByStatus(Pageable pageable, String status, JwtAuthenticationToken token);

    Page<Executions> findExecutionsByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token);

    Page<Executions> findExecutionsByFilter(Pageable pageable, String filterValue , JwtAuthenticationToken token);

    Page<Executions> findExecutionsByFilterByStatus(Pageable pageable, String filterValue, String status, JwtAuthenticationToken token);

    Page<Executions> findExecutionsByFilterByattorney(Pageable pageable, String filterValue, String attorney, JwtAuthenticationToken token);

    Page<Executions> findExecutionsByFilterByBranch(Pageable pageable, String filterValue, Long branchId, JwtAuthenticationToken token);

}
