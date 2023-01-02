package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff.DefendantPlaintiff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppealApplicantRespondentService {
//    AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token);

    List<AppealApplicantRespondent> updateAppealApplicantRespondent(long id, List<AppealApplicantRespondent> appealApplicantRespondents, JwtAuthenticationToken token) throws IllegalAccessException;

    AppealApplicantRespondent getApplicantRespondentById(long id);
    Page<AppealApplicantRespondent> getAdvocates(Pageable pageable, JwtAuthenticationToken token);
    void deleteAdvocate(long id);

    Page<AppealApplicantRespondent> getApplicantRespondentByAppealId(Pageable pageable,
                                                                     long id,
                                                                     JwtAuthenticationToken token);
}
