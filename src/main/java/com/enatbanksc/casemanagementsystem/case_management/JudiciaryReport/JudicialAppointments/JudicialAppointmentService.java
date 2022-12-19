package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface JudicialAppointmentService {
    JudicialAppointment createJudicialAppointment( JudicialAppointment judicialAppointment, JwtAuthenticationToken token) throws IllegalAccessException;

     JudicialAppointment  getJudicialAppointment(long id);
    Page<JudicialAppointment> getJudicialAppointments(Pageable pageable, JwtAuthenticationToken token);
    Page<JudicialAppointment> getJudiciaryReportByLitigationId(Pageable pageable,long id, JwtAuthenticationToken token);
    JudicialAppointment updateJudicialAppointment(long id, JudicialAppointment judicialAppointment, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteJudicialAppointment(long id, JwtAuthenticationToken token);
}
