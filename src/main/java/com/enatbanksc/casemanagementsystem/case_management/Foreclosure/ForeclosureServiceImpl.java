package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;

import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailDetails;
import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailRepository;
import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailService;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.CaseOwnerBranchClient;
import com.enatbanksc.casemanagementsystem.case_management._config.EmployeeClient;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getEmployeeID;
import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class ForeclosureServiceImpl implements ForeclosureService {
    private final ForeclosureRepository foreclosureRepository;
    private final EmailService emailService;
    private final EmailRepository emailRepository;
    private final CaseOwnerBranchClient caseOwnerBranchClient;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;
    EmailDetails details = new EmailDetails();

    @Override
    public Foreclosure createForeclosure(Foreclosure foreclosure, JwtAuthenticationToken token) throws IllegalAccessException {
        var employeeId = getEmployeeID(token);
        var maintainer = getEmployee(employeeId);
        var email = token.getTokenAttributes().get("email");
        foreclosure.setMaintainer(maintainer);
        foreclosure.getMaintainer().setEmail((String) email);
        var postForeclosure = foreclosureRepository.save(foreclosure);
        if (postForeclosure.getForeclosureId() != null) {
            details.setRecipient("mz.tesfa@gmail.com");
            details.setMsgBody("  foreclosure is created");
            details.setSubject("I'm from foreclosure");
            if (!emailService.sendSimpleMail(details).isEmpty()) {
                details.setForeclosure(postForeclosure);
                details.setSent(true);
                emailRepository.save(details);
            }
            System.out.println("emil details" + details);

        }
        return postForeclosure;

    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }


    @Override
    public Foreclosure getForeclosureById(long id) {
        return foreclosureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Foreclosure.class, "Mortgage Detail with an id " + id + " was not found!"));
    }

    @Override
    public Page<Foreclosure> getAllForeclosure(Pageable pageable, JwtAuthenticationToken token) {
        return foreclosureRepository.findAllByDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(pageable);
    }

    @Override
    public Foreclosure updateForeclosure(long id, Foreclosure foreclosure, JwtAuthenticationToken token) throws IllegalAccessException {
        var mt = getForeclosureById(id);
        BeanUtils.copyProperties(foreclosure, mt, getNullPropertyNames(foreclosure));

        var updateForeclosure = foreclosureRepository.save(mt);
        if (Objects.equals(updateForeclosure.getStatus(), "Approved") && !Objects.equals(mt.getStatus(), "Approved")) {
            details.setRecipient("mz.tesfa@gmail.com");
            details.setMsgBody("  foreclosure is Approved");
            details.setSubject("I'm from foreclosure");
            if (!emailService.sendSimpleMail(details).isEmpty()) {
                details.setForeclosure(updateForeclosure);
                details.setSent(true);
                emailRepository.save(details);
            }

        }

        return updateForeclosure;
    }

    @Override
    public void deleteForeclosureById(long id) {

        foreclosureRepository.deleteById(id);
    }

    @Override
    public Page<Foreclosure> findForeclosureByStatus(Pageable pageable, String status, JwtAuthenticationToken token) {
        return foreclosureRepository.findForeclosureByStatusAndDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(pageable, status);

    }

    @Override
    public Page<Foreclosure> findMortgageByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return foreclosureRepository.findForeclosureByAttorneyHandlingTheCaseAndDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(pageable, attorney);

    }

    @Override
    public Page<Foreclosure> findForeclosureByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token) {
        return foreclosureRepository.findForeclosureByMaintainerBranchIdAndDeletedIsFalseOrderByForeclosureIdDesc(pageable, branchId);

    }

    @Override
    public Page<Foreclosure> findAllByBranchIdIsNotContaining(Pageable pageable, Long branchId, JwtAuthenticationToken token) {
        return foreclosureRepository.findAllByBranchIdAndDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(pageable, branchId);

    }

}
