package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface JudiciaryReportService {
    JudiciaryReport createJudiciaryReport(JudiciaryReport judiciaryReport, JwtAuthenticationToken token);
    JudiciaryReport getJudiciaryReport(long id);
    JudiciaryReport updateJudiciaryReport(long id, JudiciaryReport judiciaryReport, JwtAuthenticationToken token) throws IllegalAccessException;
    Page<JudiciaryReport> getJudiciaryReports(Pageable pageable, JwtAuthenticationToken token);
    void deleteJudiciaryReport(long id);
}
