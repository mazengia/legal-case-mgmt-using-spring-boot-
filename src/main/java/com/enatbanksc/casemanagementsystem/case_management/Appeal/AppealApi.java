package com.enatbanksc.casemanagementsystem.case_management.Appeal;

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

public interface AppealApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    AppealDto createAppeal(@RequestBody @Valid AppealDto appealDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AppealDto getAppealById(@PathVariable("id") long id);

    @GetMapping("/attorney/{attorneyHandlingTheCase}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<AppealDto>> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(@Parameter(description = "pagination object", schema = @Schema(implementation = Pageable.class))
                                                                                                         @Valid Pageable pageable,
                                                                                                         @PathVariable("attorneyHandlingTheCase")   String attorneyHandlingTheCase,
                                                                                                         PagedResourcesAssembler assembler,
                                                                                                         JwtAuthenticationToken token,
                                                                                                         UriComponentsBuilder uriBuilder,
                                                                                                         final HttpServletResponse response);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AppealDto updateAppeal(@PathVariable("id") long expenseId, @RequestBody @Valid AppealDto appealDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<AppealDto>> getAllAppeal(@Parameter(description = "pagination object", schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable,
                                                       PagedResourcesAssembler assembler,
                                                       JwtAuthenticationToken token,
                                                       UriComponentsBuilder uriBuilder,
                                                       final HttpServletResponse response);
}
