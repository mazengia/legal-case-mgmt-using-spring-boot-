package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface DefendantPlaintiffService {
//    AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token);
    DefendantPlaintiff updateAdvocate(long id, DefendantPlaintiff defendantPlaintiff, JwtAuthenticationToken token) throws IllegalAccessException;
    DefendantPlaintiff getAdvocate(long id);
    Page<DefendantPlaintiff> getAdvocates(Pageable pageable, JwtAuthenticationToken token);
    void deleteAdvocate(long id);
}
