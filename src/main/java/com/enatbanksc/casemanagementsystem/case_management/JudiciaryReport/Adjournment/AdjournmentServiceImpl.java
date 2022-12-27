package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

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
public class AdjournmentServiceImpl implements AdjournmentService{
    private final AdjournmentRepository adjournmentRepository;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;


    @Override
    public Adjournment createAdjournment(Adjournment adjournment
            , JwtAuthenticationToken token
    ) {
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        adjournment.setMaintained_by(maintainer) ;
        return   adjournmentRepository.save(adjournment);
    }

    @Override
    public Adjournment getAdjournment(long id) {
        return adjournmentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Adjournment.class, "Adjournment with that id " + id + " was not found!"));
    }

    @Override
    public Adjournment updateAdjournment(long id, Adjournment adjournment
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        var i = getAdjournment(id);
        var employeeId = getEmployeeID(token);
        if(adjournment.getMaintained_by().getEmployeeId().equals(employeeId)){
            throw new IllegalAccessException("You are not allowed to update this Adjournment");
        }
        BeanUtils.copyProperties(adjournment, i, getNullPropertyNames(adjournment));
        return adjournmentRepository.save(i);
    }

    @Override
    public Page<Adjournment> getAdjournment(Pageable pageable
            , JwtAuthenticationToken token
    ) {
        return adjournmentRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public void deleteAdjournment(long id) {
        adjournmentRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
