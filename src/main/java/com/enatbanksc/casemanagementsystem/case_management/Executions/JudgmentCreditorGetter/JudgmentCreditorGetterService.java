package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JudgmentCreditorGetterService {
//    AppealApplicantRespondent createAdvocate(AppealApplicantRespondent appealApplicantRespondent, JwtAuthenticationToken token);
     JudgmentCreditorGetter getJudgmentCreditorGetterById(long id);
    Page<JudgmentCreditorGetter> getJudgmentCreditorGetter(Pageable pageable, JwtAuthenticationToken token);
    void deleteJudgmentCreditorGetter(long id);

    List<JudgmentCreditorGetter> updateJudgmentCreditorGetter(long id, List<JudgmentCreditorGetter> judgmentCreditorGetters, JwtAuthenticationToken token) throws IllegalAccessException;

    Page<JudgmentCreditorGetter> getJudgmentCreditorGetterByExecutionId(Pageable pageable,
                                                                        long litigationId,
                                                                        JwtAuthenticationToken token);
}
