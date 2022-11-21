package com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReportRepository;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationRepository;
import com.enatbanksc.casemanagementsystem.case_management.config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import com.enatbanksc.casemanagementsystem.case_management.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class InterveneServiceImpl implements InterveneService{

    private final InterveneRepository interveneRepository;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;

    private final LitigationRepository litigationRepository;


    @Override
    public List<Intervene> createIntervenes(long litigationId, List<Intervene> intervenes, JwtAuthenticationToken token) {
        var litigation = litigationRepository.findById(litigationId).get();
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        intervenes.forEach(intervene -> {
            intervene.setLitigation(litigation);
            intervene.setMaintained_by(maintainer);
        });
        return (List<Intervene>) interveneRepository.saveAll(intervenes);
    }

    @Override
    public Intervene getIntervene(long id) {
        return interveneRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Intervene.class, "Intervene with that id " + id + " was not found!"));
    }

    @Override
    public Intervene updateIntervene(long id, Intervene intervene, JwtAuthenticationToken token) throws IllegalAccessException {
        var i = getIntervene(id);
        var employeeId = getEmployeeID(token);
        if(intervene.getMaintained_by().getEmployeeId().equals(employeeId)){
            throw new IllegalAccessException("You are not allowed to update this Intervene");
        }
        BeanUtils.copyProperties(intervene, i, getNullPropertyNames(intervene));
        return interveneRepository.save(i);
    }

    @Override
    public Page<Intervene> getIntervenes(Pageable pageable, JwtAuthenticationToken token) {
        return interveneRepository.findAll(pageable);
    }

    @Override
    public void deleteIntervene(long id) {
        interveneRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
