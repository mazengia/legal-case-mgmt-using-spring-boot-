package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

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

public interface AppealApplicantRespondentApi {

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    AppealApplicantRespondentDto createAdvocate(@RequestBody @Valid AppealApplicantRespondentDto appealApplicantRespondentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AppealApplicantRespondentDto getAdvocate(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AppealApplicantRespondentDto updateAdvocate(@PathVariable("id") long id, @RequestBody @Valid AppealApplicantRespondentDto appealApplicantRespondentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<AppealApplicantRespondentDto>> getAdvocates(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                           @Valid Pageable pageable,
                                                                          PagedResourcesAssembler assembler,
                                                                          JwtAuthenticationToken token,
                                                                          UriComponentsBuilder uriBuilder,
                                                                          final HttpServletResponse response);
}
