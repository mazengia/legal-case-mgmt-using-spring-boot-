package com.enatbanksc.casemanagementsystem.case_management.JudicialAppointments;

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
    void deleteJudicialAppointment(long id);

    Page<JudicialAppointment> getAppointmentByExecutionId(Pageable pageable, long id, JwtAuthenticationToken token);

    Page<JudicialAppointment> getAppointmentByExecutionsAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<JudicialAppointment> getAppointmentByLitigationAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<JudicialAppointment> getAppointmentByExecution(Pageable pageable, JwtAuthenticationToken token);

    Page<JudicialAppointment> getAppointmentByLitigation(Pageable pageable, JwtAuthenticationToken token);
}
