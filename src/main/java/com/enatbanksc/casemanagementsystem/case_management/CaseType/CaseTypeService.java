package com.enatbanksc.casemanagementsystem.case_management.CaseType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface CaseTypeService {
    CaseType createCaseType(CaseType caseType, JwtAuthenticationToken token) throws IllegalAccessException;
    CaseType getCaseType(long caseTypeId);
    Page<CaseType> getCaseTypes(Pageable pageable, JwtAuthenticationToken token);
    CaseType updateCaseType(long caseTypeId, CaseType caseType, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteCaseType(long caseTypeId, JwtAuthenticationToken token);
}
