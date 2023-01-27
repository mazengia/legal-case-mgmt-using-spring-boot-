package com.enatbanksc.casemanagementsystem.case_management.CaseType;

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

public interface CaseTypeApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    CaseTypeDto createCaseType(@RequestBody @Valid CaseTypeDto caseTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{caseTypeId}")
    @ResponseStatus(HttpStatus.OK)
    CaseTypeDto getCaseType(@PathVariable("caseTypeId") long caseTypeId);

    @DeleteMapping("/{caseTypeId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCaseType(@PathVariable("caseTypeId") long caseTypeId);

    @PutMapping("/{caseTypeId}")
    @ResponseStatus(HttpStatus.OK)
    CaseTypeDto updateCaseType(@PathVariable("caseTypeId") long caseTypeId, @RequestBody @Valid CaseTypeDto caseTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CaseTypeDto>> getCaseTypes(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable,
                                                       PagedResourcesAssembler assembler,
                                                       JwtAuthenticationToken token,
                                                       UriComponentsBuilder uriBuilder,
                                                       final HttpServletResponse response);
}
