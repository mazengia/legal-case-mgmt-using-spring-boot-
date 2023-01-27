package com.enatbanksc.casemanagementsystem.case_management.JudicialAppointments;

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
    JudicialAppointmentDto createJudiciaryAppointment(@RequestBody @Valid JudicialAppointmentDto judicialAppointmentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    JudicialAppointmentDto getJudiciaryAppointmentById(@PathVariable("id") long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteJudiciaryAppointmentById(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    JudicialAppointmentDto updateJudiciaryAppointment(@PathVariable("id") long id, @RequestBody @Valid JudicialAppointmentDto judicialAppointmentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<JudicialAppointmentDto>> getAllJudiciaryAppointment(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                                  PagedResourcesAssembler assembler,
                                                                                  JwtAuthenticationToken token,
                                                                                  UriComponentsBuilder uriBuilder,
                                                                                  HttpServletResponse response);

    @GetMapping("/litigation-id/{id}")
    ResponseEntity<PagedModel<JudicialAppointmentDto>> getJudicialAppointmentDtoByLitigationId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                                               @PathVariable("id") long id,
                                                                                               PagedResourcesAssembler assembler,
                                                                                               JwtAuthenticationToken token,
                                                                                               UriComponentsBuilder uriBuilder,
                                                                                               HttpServletResponse response);

    @GetMapping("/execution-id/{id}")
    ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getJudicialAppointmentDtoByExecutionId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                           @PathVariable("id") long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);

    @GetMapping("/execution-attorney/{attorney}")
    ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getJudicialAppointmentByExecutionsAttorneyHandlingTheCase(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                              @PathVariable("attorney")   String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);

    @GetMapping("/litigation-attorney/{attorney}")
    ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getJudiciaryAppointmentByLitigationAttorneyHandlingTheCase(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                               @PathVariable("attorney")   String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);
    @GetMapping("/execution")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<JudicialAppointmentDto>> getALLAppointmentByExecution(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);

    @GetMapping("/litigation")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<JudicialAppointmentDto>> getALLAppointmentByLitigation(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);
}
