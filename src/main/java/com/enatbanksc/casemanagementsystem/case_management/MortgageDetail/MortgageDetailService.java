package com.enatbanksc.casemanagementsystem.case_management.MortgageDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface MortgageDetailService {
    MortgageDetail createMortgageDetail(MortgageDetail mortgageDetail, JwtAuthenticationToken token) throws IllegalAccessException;

    MortgageDetail getMortgageDetail(long id);

    Page<MortgageDetail> getMortgageDetail(Pageable pageable, JwtAuthenticationToken token);

    MortgageDetail updateMortgageDetail(long id, MortgageDetail mortgageDetail, JwtAuthenticationToken token) throws IllegalAccessException;

    void deleteMortgageDetail(long id, JwtAuthenticationToken token);

    Page<MortgageDetail> findMortgageDetailByStatus(Pageable pageable, String status, JwtAuthenticationToken token);

    Page<MortgageDetail> findMortgageByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<MortgageDetail> findMortgageDetailByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token);
}