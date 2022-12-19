package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;

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
}
