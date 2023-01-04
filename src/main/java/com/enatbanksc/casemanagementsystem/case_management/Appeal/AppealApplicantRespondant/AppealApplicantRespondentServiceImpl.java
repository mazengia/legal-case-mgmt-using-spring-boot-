package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

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

import java.util.List;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class AppealApplicantRespondentServiceImpl implements AppealApplicantRespondentService {

    private final AppealApplicantRespondentRepository appealApplicantRespondentRepository;

    private final EmployeeMapper employeeMapper;

    private final JudiciaryReportRepository judiciaryReportRepository;
    private final EmployeeClient employeeClient;

//    @Override
//    public AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token) {
//
//        return appealApplicantRespondentRepository.save(appealApplicantRespondent);
//    }

    @Override
    public List<AppealApplicantRespondent> updateAppealApplicantRespondent(long id, List<AppealApplicantRespondent> appealApplicantRespondents, JwtAuthenticationToken token) throws IllegalAccessException {

        for (AppealApplicantRespondent appealApplicantRespondent : appealApplicantRespondents) {
            var appealApplicantRespondentId = appealApplicantRespondent.getAppealApplicantRespondentId();
            var a = getApplicantRespondentById( appealApplicantRespondentId);
            BeanUtils.copyProperties(appealApplicantRespondent, a, getNullPropertyNames(appealApplicantRespondent));
            appealApplicantRespondentRepository.save(a);
        }
//        return (List<DefendantPlaintiff>) defendantPlaintiffRepository.saveAll(a);
        return null;
    }

    @Override
    public AppealApplicantRespondent getApplicantRespondentById(long id) {
        return appealApplicantRespondentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(AppealApplicantRespondent.class, "Advocate with an id " + id + " was not found!"));
    }

    @Override
    public Page<AppealApplicantRespondent> getAdvocates(Pageable pageable, JwtAuthenticationToken token) {
        return appealApplicantRespondentRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public void deleteAdvocate(long id) {
        appealApplicantRespondentRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }

    @Override
    public Page<AppealApplicantRespondent> getApplicantRespondentByAppealId(Pageable pageable,
                                                                            long id,
                                                                            JwtAuthenticationToken token) {
        return appealApplicantRespondentRepository.findAllByAppealAppealIdOrderByCreatedAtDesc(id, pageable);
    }
}
