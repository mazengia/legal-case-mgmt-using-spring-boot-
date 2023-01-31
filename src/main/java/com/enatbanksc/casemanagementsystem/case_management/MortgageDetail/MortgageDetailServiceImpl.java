package com.enatbanksc.casemanagementsystem.case_management.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailDetails;
import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailRepository;
import com.enatbanksc.casemanagementsystem.case_management.SentEmail.EmailService;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class MortgageDetailServiceImpl implements MortgageDetailService {
    private final MortgageDetailRepository mortgageDetailRepository;
    private final EmailService emailService;
    private final EmailRepository emailRepository;
    EmailDetails details = new EmailDetails();
    @Override
    public MortgageDetail createMortgageDetail(MortgageDetail mortgageDetail, JwtAuthenticationToken token) throws IllegalAccessException {

        var postMortgageDetail = mortgageDetailRepository.save(mortgageDetail);
        if (postMortgageDetail.getMortgageDetailId() != null) {
            details.setRecipient("mz.tesfa@gmail.com");
            details.setMsgBody("  foreclosure is created");
            details.setSubject("I'm from foreclosure");
            if (!emailService.sendSimpleMail(details).isEmpty()) {
                details.setMortgageDetail(postMortgageDetail);
                details.setSent(true);
                emailRepository.save(details);
            }
            System.out.println("emil details" + details);

        }

        return postMortgageDetail;

//        return mortgageDetailRepository.save(mortgageDetail);
    }

    @Override
    public MortgageDetail getMortgageDetail(long id) {
        return mortgageDetailRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MortgageDetail.class, "Mortgage Detail with an id " + id + " was not found!"));
    }

    @Override
    public Page<MortgageDetail> getMortgageDetail(Pageable pageable, JwtAuthenticationToken token) {
        return mortgageDetailRepository.findAllByDeletedIsFalseOrderByMortgageDetailIdDesc(pageable);
    }

    @Override
    public MortgageDetail updateMortgageDetail(long id, MortgageDetail mortgageDetail, JwtAuthenticationToken token) throws IllegalAccessException {
        var mt = getMortgageDetail(id);
        BeanUtils.copyProperties(mortgageDetail, mt, getNullPropertyNames(mortgageDetail));

        var updateMortgageDetail = mortgageDetailRepository.save(mt);
        if (Objects.equals(updateMortgageDetail.getStatus(), "Approved") && !Objects.equals(mt.getStatus(), "Approved")) {
            details.setRecipient("mz.tesfa@gmail.com");
            details.setMsgBody("  foreclosure is Approved");
            details.setSubject("I'm from foreclosure");
            if (!emailService.sendSimpleMail(details).isEmpty()) {
                details.setMortgageDetail(updateMortgageDetail);
                details.setSent(true);
                emailRepository.save(details);
            }

        }

        return updateMortgageDetail;

//        return mortgageDetailRepository.save(mt);
    }

    @Override
    public void deleteMortgageDetailById(long id) {

        mortgageDetailRepository.deleteById(id);
    }

    @Override
    public Page<MortgageDetail> findMortgageDetailByStatus(Pageable pageable, String status, JwtAuthenticationToken token) {
        return mortgageDetailRepository.findMortgageDetailByStatusAndDeletedIsFalseOrderByMortgageDetailIdDesc(pageable, status);

    }

    @Override
    public Page<MortgageDetail> findMortgageByAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return mortgageDetailRepository.findMortgageDetailByAttorneyHandlingTheCaseAndDeletedIsFalseOrderByMortgageDetailIdDesc(pageable, attorney);

    }
    @Override
    public Page<MortgageDetail> findMortgageDetailByBranchId(Pageable pageable, Long branchId, JwtAuthenticationToken token) {
        return mortgageDetailRepository.findMortgageDetailByBranchIdAndDeletedIsFalseOrderByMortgageDetailIdDesc(pageable, branchId);

    }
    @Override
    public Page<MortgageDetail> findAllByBranchIdIsNotContaining(Pageable pageable, Long branchId, JwtAuthenticationToken token) {
        return mortgageDetailRepository.findAllByBranchIdAndDeletedIsFalseOrderByMortgageDetailIdDesc(pageable, branchId);

    }

}
