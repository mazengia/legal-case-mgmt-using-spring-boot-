package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

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
public class JudgmentCreditorGetterServiceImpl implements JudgmentCreditorGetterService {

    private final JudgmentCreditorGetterRepository judgmentCreditorGetterRepository;

    private final EmployeeMapper employeeMapper;

    private final JudiciaryReportRepository judiciaryReportRepository;
    private final EmployeeClient employeeClient;

//    @Override
//    public AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token) {
//
//        return appealApplicantRespondentRepository.save(appealApplicantRespondent);
//    }



    @Override
    public JudgmentCreditorGetter getJudgmentCreditorGetterById(long id) {
        return judgmentCreditorGetterRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(JudgmentCreditorGetter.class, "Advocate with an id " + id + " was not found!"));
    }

    @Override
    public Page<JudgmentCreditorGetter> getJudgmentCreditorGetter(Pageable pageable, JwtAuthenticationToken token) {
        return judgmentCreditorGetterRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public void deleteJudgmentCreditorGetter(long id) {
        judgmentCreditorGetterRepository.deleteById(id);
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }


    @Override
    public List<JudgmentCreditorGetter> updateJudgmentCreditorGetter(long id, List<JudgmentCreditorGetter> judgmentCreditorGetters, JwtAuthenticationToken token) throws IllegalAccessException {

        for (JudgmentCreditorGetter judgmentCreditorGetter : judgmentCreditorGetters) {
            var JudgmentCreditorGetterId = judgmentCreditorGetter.getJudgmentCreditorGetterId();
            var a = getJudgmentCreditorGetterById( JudgmentCreditorGetterId);
            BeanUtils.copyProperties(judgmentCreditorGetter, a, getNullPropertyNames(judgmentCreditorGetter));
            judgmentCreditorGetterRepository.save(a);
        }
        return null;
    }
    @Override
    public Page<JudgmentCreditorGetter> getJudgmentCreditorGetterByExecutionId(Pageable pageable,
                                                                               long litigationId,
                                                                               JwtAuthenticationToken token) {
        return judgmentCreditorGetterRepository.findAllByExecutionsExecutionsIdOrderByCreatedAtDesc(litigationId, pageable);
    }
}
