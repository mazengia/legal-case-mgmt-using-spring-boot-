package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface ForeclosureService {
    Foreclosure createForeclosure(Foreclosure foreclosure, JwtAuthenticationToken token) throws IllegalAccessException;

    Foreclosure getForeclosureById(long id);

    Page<Foreclosure> getAllForeclosure(Pageable pageable, JwtAuthenticationToken token);

    Foreclosure updateForeclosure(long id, Foreclosure foreclosure, JwtAuthenticationToken token) throws IllegalAccessException;

    void deleteForeclosureById(long id);

    Page<Foreclosure> findForeclosureByStatus(Pageable pageable, String status, JwtAuthenticationToken token);

    Page<Foreclosure> findMortgageByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<Foreclosure> findForeclosureByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token);

    Page<Foreclosure> findAllByBranchIdIsNotContaining(Pageable pageable, Long branchId, JwtAuthenticationToken token);
}
