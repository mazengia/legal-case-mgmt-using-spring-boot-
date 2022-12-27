package com.enatbanksc.casemanagementsystem.case_management.CaseType;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.Util;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class CaseTypeServiceImpl implements CaseTypeService{
    private final CaseTypeRepository caseTypeRepository;
    @Override
    public CaseType createCaseType(CaseType caseType, JwtAuthenticationToken token) throws IllegalAccessException {
        caseType.setCaseTypeColor(Util.getRandomColor());
        System.out.println(caseType.getCaseTypeColor());
        return caseTypeRepository.save(caseType);
    }

    @Override
    public CaseType getCaseType(long caseTypeId) {
        return caseTypeRepository.findById(caseTypeId).orElseThrow(()-> new EntityNotFoundException(CaseType.class, "Case Type with an Id " + caseTypeId + " was not found!"));
    }

    @Override
    public Page<CaseType> getCaseTypes(Pageable pageable, JwtAuthenticationToken token) {
        return caseTypeRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public CaseType updateCaseType(long caseTypeId, CaseType caseType, JwtAuthenticationToken token) throws IllegalAccessException {
       var ct = getCaseType(caseTypeId);
        BeanUtils.copyProperties(caseType, ct, getNullPropertyNames(caseType));
        return caseTypeRepository.save(ct);
    }

    @Override
    public void deleteCaseType(long id, JwtAuthenticationToken token) {
        caseTypeRepository.deleteById(id);
    }
}
