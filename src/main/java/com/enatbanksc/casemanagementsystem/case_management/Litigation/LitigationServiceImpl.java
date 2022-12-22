package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management._config.CaseOwnerBranchClient;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import com.enatbanksc.casemanagementsystem.case_management._config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class LitigationServiceImpl implements LitigationService {

    private final LitigationRepository litigationRepository;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;
    private final CaseOwnerBranchClient caseOwnerBranchClient;


    @Override
    public Litigation createLitigation(Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException {
        var branch = getBranchById(litigation.getBranch().getId());
        litigation.setBranch(branch);


        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
//        intervenes.setMaintained_by(maintainer);
        return litigationRepository.save(litigation);
    }

    @Override
    public Litigation getLitigation(long id) {
        return litigationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Litigation.class, "Litigation with an Id " + id + "was not found!"));
    }

    @Override
    public Page<Litigation> getLitigations(Pageable pageable) {
        return litigationRepository.findAll(pageable);
    }

    @Override
    public Litigation updateLitigation(long id, Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException {
        var l = getLitigation(id);
        var branch = getBranchById(litigation.getBranch().getId());
        litigation.setBranch(branch);
        BeanUtils.copyProperties(litigation, l, getNullPropertyNames(litigation));
        return litigationRepository.save(l);
    }



    @Override
    public void deleteLitigation(long id, JwtAuthenticationToken token) {
        litigationRepository.deleteById(id);
    }

    @Override
    public Page<Litigation> getLitigationByCaseStage(Pageable pageable, CaseStage caseStage, JwtAuthenticationToken token) {
        return  litigationRepository.findLitigationByCaseStage(pageable,caseStage);

    }
    @Override
    public Page<Litigation> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  litigationRepository.findLitigationByAttorneyHandlingTheCase(pageable,attorney);

    }
    @Override
    public Page<Litigation> findLitigationByStatus(Pageable pageable, String status, JwtAuthenticationToken token) {
        return  litigationRepository.findLitigationByStatus(pageable,status);

    }



    @Override
    public Page<Litigation> findLitigationByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token) {
        return  litigationRepository.findLitigationByBranchId(pageable,branchId);

    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }

    private CaseOwnerBranchDto getBranchById(Long id) {
        return caseOwnerBranchClient.getBranchById(id);
    }

    private LitigationEmployee getLitigationEmployee(String employeeId) {
        return employeeClient.getEmployee(employeeId);
    }
}
