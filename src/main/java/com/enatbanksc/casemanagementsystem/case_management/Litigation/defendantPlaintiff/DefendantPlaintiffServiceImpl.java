package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

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

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class DefendantPlaintiffServiceImpl implements DefendantPlaintiffService {

    private final DefendantPlaintiffRepository defendantPlaintiffRepository;

    private final EmployeeMapper employeeMapper;

    private final JudiciaryReportRepository judiciaryReportRepository;
    private final EmployeeClient employeeClient;

//    @Override
//    public AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token) {
//
//        return appealApplicantRespondentRepository.save(appealApplicantRespondent);
//    }

    @Override
    public DefendantPlaintiff updateAdvocate(long id, DefendantPlaintiff defendantPlaintiff, JwtAuthenticationToken token) throws IllegalAccessException {
        var a = getAdvocate(id);
//        var employeeId = getEmployeeID(token);
//        if(!a.getMaintained_by().getEmployeeId().equals(employeeId)){
//            throw new IllegalAccessException("You are not allowed to modify this advocate information!");
//        }
        BeanUtils.copyProperties(defendantPlaintiff, a, getNullPropertyNames(defendantPlaintiff));
        return defendantPlaintiffRepository.save(a);

    }

    @Override
    public DefendantPlaintiff getAdvocate(long id) {
        return defendantPlaintiffRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(DefendantPlaintiff.class, "Advocate with an id " + id + " was not found!"));
    }

    @Override
    public Page<DefendantPlaintiff> getAdvocates(Pageable pageable, JwtAuthenticationToken token) {
        return defendantPlaintiffRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public void deleteAdvocate(long id) {
        defendantPlaintiffRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
