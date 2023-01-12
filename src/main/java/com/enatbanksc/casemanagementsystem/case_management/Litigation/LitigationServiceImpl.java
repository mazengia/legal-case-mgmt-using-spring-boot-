package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management.Comment.Comment;
import com.enatbanksc.casemanagementsystem.case_management.Comment.CommentRepository;
import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailDetails;
import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailRepository;
import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailService;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management._config.CaseOwnerBranchClient;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
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
public class LitigationServiceImpl implements LitigationService {

    private final LitigationRepository litigationRepository;
    private final EmployeeClient employeeClient;
    private final EmployeeMapper employeeMapper;
    private final CaseOwnerBranchClient caseOwnerBranchClient;
    private final CommentRepository commentRepository;

    EmailDetails details = new EmailDetails();
    private final EmailService emailService;
    private final EmailRepository emailRepository;

    @Override
    public Litigation createLitigation(Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException {

        var branch = getBranchById(litigation.getBranch().getId());
        Comment comment =new Comment();
        litigation.setBranch(branch);
        var employeeId = getEmployeeID(token);
        var postLitigation = litigationRepository.save(litigation);
        if (postLitigation.getLitigationId() != null) {
            comment.setLitigation(postLitigation);
            comment.setContent(postLitigation.getContent());
            commentRepository.save(comment);
            details.setRecipient("mz.tesfa@gmail.com");
            details.setMsgBody("Litigation is created");
            details.setSubject("I'm from Litigation");
//            emailService.sendSimpleMail(details);
            if (!emailService.sendSimpleMail(details).isEmpty()) {
                details.setLitigation(postLitigation);
                details.setSent(true);
                emailRepository.save(details);
            }
            System.out.println("emil details" + details);

        }

        return postLitigation; 
    }

    @Override
    public Litigation getLitigationById(long id) {
        return litigationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Litigation.class, "Litigation with an Id " + id + "was not found!"));
    }

    @Override
    public Page<Litigation> getAllLitigation(Pageable pageable) {
        return litigationRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Litigation updateLitigation(long id, Litigation litigation, JwtAuthenticationToken token) throws IllegalAccessException {
        var l = getLitigationById(id);
        BeanUtils.copyProperties(litigation, l, getNullPropertyNames(litigation));



        var updateLitigation = litigationRepository.save(l);
        if (Objects.equals(updateLitigation.getStatus(), "Approved") && !Objects.equals(l.getStatus(), "Approved")) {
            details.setRecipient("mz.tesfa@gmail.com");
            details.setMsgBody("  Litigation is Approved");
            details.setSubject("I'm from Litigation");
            if (!emailService.sendSimpleMail(details).isEmpty()) {
                details.setLitigation(updateLitigation);
                details.setSent(true);
                emailRepository.save(details);
            }

        }
        if (!Objects.equals(updateLitigation.getAttorneyHandlingTheCase(), l.getAttorneyHandlingTheCase())) {
            details.setRecipient("mz.tesfa@gmail.com");
            details.setMsgBody("  Litigation is assigned to"+updateLitigation.getAttorneyHandlingTheCase());
            details.setSubject("I'm from Litigation");
            if (!emailService.sendSimpleMail(details).isEmpty()) {
                details.setLitigation(updateLitigation);
                details.setSent(true);
                emailRepository.save(details);
            }
        }

        return updateLitigation;


//        return litigationRepository.save(l);
    }


    @Override
    public void deleteLitigation(long id, JwtAuthenticationToken token) {
        litigationRepository.deleteById(id);
    }

    @Override
    public Page<Litigation> getLitigationByCaseStage(Pageable pageable, CaseStage caseStage, JwtAuthenticationToken token) {
        return litigationRepository.findLitigationByCaseStageOrderByCreatedAtDesc(pageable, caseStage);

    }

    @Override
    public Page<Litigation> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return litigationRepository.findLitigationByAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable, attorney);

    }

    @Override
    public Page<Litigation> findLitigationByStatus(Pageable pageable, String status, JwtAuthenticationToken token) {
        return litigationRepository.findLitigationByStatusOrderByCreatedAtDesc(pageable, status);

    }


    @Override
    public Page<Litigation> findLitigationByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token) {
        return litigationRepository.findLitigationByBranchIdOrderByCreatedAtDesc(pageable, branchId);

    }
    @Override
    public Page<Litigation> findAllByBranchIdIsNotContaining(Pageable pageable, long branchId, JwtAuthenticationToken token) {
        return litigationRepository.findAllByBranchIdOrderByCreatedAtDesc(pageable, branchId);

    }

    @Override
    public Page<Litigation> findLitigationByFilter(Pageable pageable, String filterValue, JwtAuthenticationToken token) {
        return litigationRepository.findByCaseTypeOrCourtAdjudicatingOrStatusOrAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable, filterValue);

    }

    @Override
    public Page<Litigation> findLitigationByFilterByStatus(Pageable pageable, String filterValue, String status, JwtAuthenticationToken token) {
        return litigationRepository.findAllByCaseTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseAndStatusOrderByCreatedAtDesc(pageable, filterValue, status);
//    return  null;
    }

    @Override
    public Page<Litigation> findLitigationByFilterByattorney(Pageable pageable, String filterValue, String attorney, JwtAuthenticationToken token) {
        return litigationRepository.findAllByCaseTypeOrCourtAdjudicatingOrStatusAndAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable, filterValue, attorney);
//return  null;
    }

    @Override
    public Page<Litigation> findLitigationByFilterByBranch(Pageable pageable, String filterValue, Long branchId, JwtAuthenticationToken token) {

        return litigationRepository.findByCaseTypeOrCourtAdjudicatingOrStatusOrAttorneyHandlingTheCaseAndBranchIdOrderByCreatedAtDesc(pageable, filterValue, branchId);
//   return null;
    }

    private Employee getEmployee(String employeeId) {
        return employeeMapper.employeeDtoToEmployee(employeeClient.getEmployeeById(employeeId));
    }

    private CaseOwnerBranchDto getBranchById(Long id) {
        return caseOwnerBranchClient.getBranchById(id);
    }

    private LitigationEmployee getLitigationEmployee(String employeeId) {
        return employeeClient.getEmployee(employeeId);
    }
}
