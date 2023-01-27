package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DefendantPlaintiffService {
//    AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token);
    List<DefendantPlaintiff> updateDefendantPlaintiff(long id, List<DefendantPlaintiff> defendantPlaintiff, JwtAuthenticationToken token) throws IllegalAccessException;
    DefendantPlaintiff getDefendantPlaintiffById(long id);

    Page<DefendantPlaintiff> getDefendantPlaintiffByLitigationId(Pageable pageable,
                                                                 long litigationId,
                                                                 JwtAuthenticationToken token);

    Page<DefendantPlaintiff> getAllDefendantPlaintiff(Pageable pageable, JwtAuthenticationToken token);
    void deleteDefendantPlaintiffById(long id);
}
