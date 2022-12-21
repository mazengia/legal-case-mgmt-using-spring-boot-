package com.enatbanksc.casemanagementsystem.case_management.Intervene;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
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
public class InterveneServiceImpl implements InterveneService{

    private final InterveneRepository interveneRepository;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;


    @Override
    public Intervene createIntervenes( Intervene intervenes
            , JwtAuthenticationToken token
    ) {
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        intervenes.setMaintained_by(maintainer);

        return   interveneRepository.save(intervenes);
    }

    @Override
    public Intervene getIntervene(long id) {
        return interveneRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Intervene.class, "Intervene with that id " + id + " was not found!"));
    }

    @Override
    public Intervene updateIntervene(long id, Intervene intervene
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        var i = getIntervene(id);
//        var employeeId = getEmployeeID(token);
//        if(intervene.getMaintained_by().getEmployeeId().equals(employeeId)){
//            throw new IllegalAccessException("You are not allowed to update this Intervene");
//        }
        BeanUtils.copyProperties(intervene, i, getNullPropertyNames(intervene));
        return interveneRepository.save(i);
    }

    @Override
    public Page<Intervene> getIntervenes(Pageable pageable
            , JwtAuthenticationToken token
    ) {
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
