package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface JudicialAppointmentApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    JudicialAppointmentDto createJudiciaryReport(@RequestBody @Valid JudicialAppointmentDto judicialAppointmentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    JudicialAppointmentDto getJudiciaryReport(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    JudicialAppointmentDto updateJudiciaryReportDto(@PathVariable("id") long id, @RequestBody @Valid JudicialAppointmentDto judicialAppointmentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<JudicialAppointmentDto>> getJudiciaryReports(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                           PagedResourcesAssembler assembler,
                                                                           JwtAuthenticationToken token,
                                                                           UriComponentsBuilder uriBuilder,
                                                                           HttpServletResponse response);

    @GetMapping("/litigation-appointment/{id}")
    ResponseEntity<PagedModel<JudicialAppointmentDto>> getJudiciaryReportByLitigationId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                                        @PathVariable("id") long id,
                                                                                        PagedResourcesAssembler assembler,
                                                                                        JwtAuthenticationToken token,
                                                                                        UriComponentsBuilder uriBuilder,
                                                                                        HttpServletResponse response);

}
