package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface AppealApplicantRespondentService {
//    AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token);
    AppealApplicantRespondent updateAdvocate(long id, AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token) throws IllegalAccessException;
    AppealApplicantRespondent getAdvocate(long id);
    Page<AppealApplicantRespondent> getAdvocates(Pageable pageable, JwtAuthenticationToken token);
    void deleteAdvocate(long id);
}
