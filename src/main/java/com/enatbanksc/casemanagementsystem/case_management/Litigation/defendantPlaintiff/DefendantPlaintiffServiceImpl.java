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

import java.util.List;

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
    public List<DefendantPlaintiff> updateDefendantPlaintiff(long id, List<DefendantPlaintiff> defendantPlaintiff, JwtAuthenticationToken token) throws IllegalAccessException {
        System.out.println("plentiff");
        System.out.println(defendantPlaintiff);
        System.out.println("plentiff");
        for (DefendantPlaintiff defendantPlaintiff1 : defendantPlaintiff) {
            var defendantPlaintiffId = defendantPlaintiff1.getDefendantPlaintiffId();
            var a = getDefendantPlaintiffById(defendantPlaintiffId);
            BeanUtils.copyProperties(defendantPlaintiff1, a, getNullPropertyNames(defendantPlaintiff1));
            defendantPlaintiffRepository.save(a);
        }
//        return (List<DefendantPlaintiff>) defendantPlaintiffRepository.saveAll(a);
        return null;
    }

    @Override
    public DefendantPlaintiff getDefendantPlaintiffById(long id) {
        return defendantPlaintiffRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(DefendantPlaintiff.class, "Advocate with an id " + id + " was not found!"));
    }

    @Override
    public Page<DefendantPlaintiff> getDefendantPlaintiffByLitigationId(Pageable pageable,
                                                                        long litigationId,
                                                                        JwtAuthenticationToken token) {
        return defendantPlaintiffRepository.findAllByLitigationLitigationIdAndDeletedIsFalseOrderByCreatedAtDesc(litigationId, pageable);
    }

    @Override
    public Page<DefendantPlaintiff> getAllDefendantPlaintiff(Pageable pageable, JwtAuthenticationToken token) {
        return defendantPlaintiffRepository.findAllByDeletedIsFalseOrderByCreatedAtDesc(pageable);
    }

    @Override
    public void deleteDefendantPlaintiffById(long id) {
        defendantPlaintiffRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }
}
