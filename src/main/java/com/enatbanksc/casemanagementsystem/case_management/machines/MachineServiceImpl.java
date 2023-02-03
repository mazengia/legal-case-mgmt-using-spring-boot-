package com.enatbanksc.casemanagementsystem.case_management.machines;

import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.ForeclosureServiceImpl;
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
public class MachineServiceImpl implements MachineService {
    private final MachineRepository machineRepository;
    private final ForeclosureServiceImpl mortgageDetailService;

    @Override
    public MachineType machineType(long mortgageId, MachineType machineType, JwtAuthenticationToken token) throws IllegalAccessException {
        var md = mortgageDetailService.getForeclosureById(mortgageId);
        machineType.setForeclosure(md);
        return machineRepository.save(machineType);
    }

    @Override
    public MachineType getCaseType(long caseTypeId) {
        return machineRepository.findById(caseTypeId).orElseThrow(() -> new EntityNotFoundException(MachineType.class, "Case Type with an Id " + caseTypeId + " was not found!"));
    }

    @Override
    public Page<MachineType> getCaseTypes(Pageable pageable, JwtAuthenticationToken token) {
        return machineRepository.findAllByDeletedIsFalseOrderByCreatedAtDesc(pageable);
    }

    @Override
    public MachineType updateCaseType(long caseTypeId, MachineType machineType, JwtAuthenticationToken token) throws IllegalAccessException {
        var ct = getCaseType(caseTypeId);
        BeanUtils.copyProperties(machineType, ct, getNullPropertyNames(machineType));
        return machineRepository.save(ct);
    }

    @Override
    public void deleteCaseType(long id) {
        machineRepository.deleteById(id);
    }
}
