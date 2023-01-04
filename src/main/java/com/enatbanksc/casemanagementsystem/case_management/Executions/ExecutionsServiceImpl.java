package com.enatbanksc.casemanagementsystem.case_management.Executions;

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
public class ExecutionsServiceImpl implements ExecutionsService {

    private final ExecutionsRepository executionsRepository;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;
    private final CaseOwnerBranchClient caseOwnerBranchClient;


    @Override
    public Executions createExecutions(Executions executions, JwtAuthenticationToken token) throws IllegalAccessException {
        var branch = getBranchById(executions.getBranch().getId());
        executions.setBranch(branch);


        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
//        intervenes.setMaintained_by(maintainer);
        return executionsRepository.save(executions);
    }

    @Override
    public Executions getExecutionsById(long id) {
        return executionsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Executions.class, "Litigation with an Id " + id + "was not found!"));
    }

    @Override
    public Page<Executions> getExecutions(Pageable pageable) {
        return executionsRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Executions updateExecutions(long id, Executions executions, JwtAuthenticationToken token) throws IllegalAccessException {
        var l = getExecutionsById(id);
//        var branch = getBranchById(litigation.getBranch().getId());
//        litigation.setBranch(branch);
        BeanUtils.copyProperties(executions, l, getNullPropertyNames(executions));
        return executionsRepository.save(l);
    }



    @Override
    public void deleteLitigation(long id, JwtAuthenticationToken token) {
        executionsRepository.deleteById(id);
    }

    @Override
    public Page<Executions> getExecutionsByCaseStage(Pageable pageable, CaseStage caseStage, JwtAuthenticationToken token) {
//        return  executionsRepository.findLitigationByCaseStageOrderByCreatedAtDesc(pageable,caseStage);
        return null;
    }
    @Override
    public Page<Executions> findExecutionsByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  executionsRepository.findExecutionsByAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorney);
//        return null;
    }
    @Override
    public Page<Executions> findExecutionsByStatus(Pageable pageable, String status, JwtAuthenticationToken token) {
//        return  executionsRepository.findLitigationOrderByCreatedAtDesc(pageable,status);
        return null;
    }



    @Override
    public Page<Executions> findExecutionsByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token) {
//        return  executionsRepository.findLitigationByBranchIdOrderByCreatedAtDesc(pageable,branchId);
        return null;
    }

    @Override
    public Page<Executions> findExecutionsByFilter(Pageable pageable, String filterValue , JwtAuthenticationToken token) {
//        return  executionsRepository.findByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,filterValue);
        return null;
    }

    @Override
    public Page<Executions> findExecutionsByFilterByStatus(Pageable pageable, String filterValue, String status, JwtAuthenticationToken token) {
//        return executionsRepository.findAllByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,filterValue,status);
    return  null;
    }

    @Override
    public Page<Executions> findExecutionsByFilterByattorney(Pageable pageable, String filterValue, String attorney, JwtAuthenticationToken token) {
//        return executionsRepository.findAllByLitigationTypeOrCourtAdjudicatingAndAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,filterValue,attorney);
return  null;
    }

    @Override
    public Page<Executions> findExecutionsByFilterByBranch(Pageable pageable, String filterValue, Long branchId, JwtAuthenticationToken token) {

//        return executionsRepository.findByLitigationTypeOrCourtAdjudicatingOrOrAttorneyHandlingTheCaseAndBranchIdOrderByCreatedAtDesc(pageable,filterValue,branchId);
   return null;
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
