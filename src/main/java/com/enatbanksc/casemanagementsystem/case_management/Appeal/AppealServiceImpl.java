package com.enatbanksc.casemanagementsystem.case_management.Appeal;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
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
public class AppealServiceImpl implements AppealService {
    private final AppealRepository appealRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeClient employeeClient;
    @Override
    public Appeal createAppeal(Appeal appeal, JwtAuthenticationToken token) throws IllegalAccessException {
        System.out.println("appeal");
        System.out.println(appeal);
        System.out.println("appeal");
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        appeal.setMaintained_by(maintainer);
        return appealRepository.save(appeal);
    }

    @Override
    public Appeal getAppealById(long id) {
        return appealRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Appeal.class, "  Type with an id: " + id + " was not found!"));
    }

    @Override
    public Page<Appeal> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable,String attorneyHandlingTheCase,JwtAuthenticationToken token) {
        return appealRepository.findAllByLitigationAttorneyHandlingTheCaseAndDeletedIsFalseOrderByAppealIdDesc(pageable,attorneyHandlingTheCase);

    }
    @Override
    public Page<Appeal> findAllByLitigationId(Pageable pageable,long id,JwtAuthenticationToken token) {
        return appealRepository.findAllByLitigationLitigationIdAndDeletedIsFalseOrderByAppealIdDesc(pageable,id);

    }
    @Override
    public Page<Appeal> getAllAppeal(Pageable pageable, JwtAuthenticationToken token) {
        return appealRepository.findAllByAndDeletedIsFalseOrderByAppealIdDesc(pageable);
    }

    @Override
    public Appeal updateAppeal(long id, Appeal appealType, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getAppealById(id);
        var employeeId = getEmployeeID(token);
        if(!et.getMaintained_by().getEmployeeId().equals(employeeId)){
            throw new IllegalAccessException("You are not allowed to update this object.");
        }
        BeanUtils.copyProperties(appealType, et, getNullPropertyNames(appealType));
        return appealRepository.save(et);
    }

    @Override
    public void deleteAppealById(long id) {
        appealRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
