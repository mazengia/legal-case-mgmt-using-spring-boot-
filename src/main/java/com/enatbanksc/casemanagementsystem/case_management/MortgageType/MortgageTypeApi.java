package com.enatbanksc.casemanagementsystem.case_management.MortgageType;

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

public interface MortgageTypeApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    MortgageTypeDto createMortgageType(@RequestBody @Valid MortgageTypeDto mortgageTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{mortgageTypeId}")
    @ResponseStatus(HttpStatus.OK)
    MortgageTypeDto getMortgageType(@PathVariable("mortgageTypeId") long mortgageTypeId);

    @PutMapping("/{mortgageTypeId}")
    @ResponseStatus(HttpStatus.OK)
    MortgageTypeDto updateMortgageType(@PathVariable("mortgageTypeId") long mortgageTypeId, @RequestBody @Valid MortgageTypeDto mortgageTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MortgageTypeDto>> getMortgageType(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                             @Valid Pageable pageable,
                                                             PagedResourcesAssembler assembler,
                                                             JwtAuthenticationToken token,
                                                             UriComponentsBuilder uriBuilder,
                                                             final HttpServletResponse response);
}

