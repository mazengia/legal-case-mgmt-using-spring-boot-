package com.enatbanksc.casemanagementsystem.case_management.machines;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface MachineService {
    MachineType machineType(long mortgageId,MachineType machineType, JwtAuthenticationToken token) throws IllegalAccessException;
    MachineType getCaseType(long caseTypeId);
    Page<MachineType> getCaseTypes(Pageable pageable, JwtAuthenticationToken token);
    MachineType updateCaseType(long caseTypeId, MachineType machineType, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteCaseType(long caseTypeId, JwtAuthenticationToken token);
}
