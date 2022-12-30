package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface JudgmentCreditorGetterService {
//    AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token);
    JudgmentCreditorGetter updateAdvocate(long id, JudgmentCreditorGetter judgmentCreditorGetter, JwtAuthenticationToken token) throws IllegalAccessException;
    JudgmentCreditorGetter getAdvocate(long id);
    Page<JudgmentCreditorGetter> getAdvocates(Pageable pageable, JwtAuthenticationToken token);
    void deleteAdvocate(long id);
}
