package com.enatbanksc.casemanagementsystem.case_management.MortgageType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface MortgageTypeService {
    MortgageType createMortgageType(MortgageType mortgageType, JwtAuthenticationToken token) throws IllegalAccessException;
    MortgageType getMortgageType(long id);
    Page<MortgageType> getMortgageTypes(Pageable pageable, JwtAuthenticationToken token);
    MortgageType updateMortgageType(long id, MortgageType mortgageType, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteMortgageType(long id, JwtAuthenticationToken token);
}
