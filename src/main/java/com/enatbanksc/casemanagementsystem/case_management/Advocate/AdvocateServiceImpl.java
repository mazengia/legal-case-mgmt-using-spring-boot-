package com.enatbanksc.casemanagementsystem.case_management.Advocate;

import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReportRepository;
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
public class AdvocateServiceImpl implements AdvocateService{

    private final AdvocateRepository advocateRepository;

    private final EmployeeMapper employeeMapper;

    private final JudiciaryReportRepository judiciaryReportRepository;
    private final EmployeeClient employeeClient;

    @Override
    public Advocate createAdvocate(Advocate advocate, JwtAuthenticationToken token) {
//       var report =  judiciaryReportRepository.findById(reportId);
       var employeeId = getEmployeeID(token);
       var employee = getEmployee(employeeId);
       advocate.setMaintained_by(employee);
//       advocate.setAdvocateId(advocate.get());
        return advocateRepository.save(advocate);
    }

    @Override
    public Advocate updateAdvocate(long id, Advocate advocate, JwtAuthenticationToken token) throws IllegalAccessException {
        var a = getAdvocate(id);
        var employeeId = getEmployeeID(token);
        if(!a.getMaintained_by().getEmployeeId().equals(employeeId)){
            throw new IllegalAccessException("You are not allowed to modify this advocate information!");
        }
        BeanUtils.copyProperties(advocate, a, getNullPropertyNames(advocate));
        return advocateRepository.save(a);

    }

    @Override
    public Advocate getAdvocate(long id) {
        return advocateRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Advocate.class, "Advocate with an id " + id + " was not found!"));
    }

    @Override
    public Page<Advocate> getAdvocates(Pageable pageable, JwtAuthenticationToken token) {
        return advocateRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public void deleteAdvocate(long id) {
        advocateRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
