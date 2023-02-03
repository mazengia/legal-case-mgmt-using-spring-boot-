package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;

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

public interface ForeclosureApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ForeclosureDto createForeclosure(@RequestBody @Valid ForeclosureDto foreclosureDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/attorney/{attorney}")
    ResponseEntity<PagedModel<ForeclosureDto>> findMortgageByAttorneyHandlingTheCase(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                                     @PathVariable("attorney") String attorney,
                                                                                     PagedResourcesAssembler assembler,
                                                                                     JwtAuthenticationToken token,
                                                                                     UriComponentsBuilder uriBuilder,
                                                                                     HttpServletResponse response);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ForeclosureDto getForeclosureById(@PathVariable("id") long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteForeclosureById(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ForeclosureDto updateForeclosureById(@PathVariable("id") long id, @RequestBody @Valid ForeclosureDto foreclosureDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ForeclosureDto>> getAllForeclosure(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                       @Valid Pageable pageable,
                                                                 PagedResourcesAssembler assembler,
                                                                 JwtAuthenticationToken token,
                                                                 UriComponentsBuilder uriBuilder,
                                                                 final HttpServletResponse response);

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ForeclosureDto>> getForeclosureByStatus(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                            @Valid Pageable pageable,
                                                                      @PathVariable("status") String status,
                                                                      PagedResourcesAssembler assembler,
                                                                      JwtAuthenticationToken token,
                                                                      UriComponentsBuilder uriBuilder,
                                                                      final HttpServletResponse response);

    @GetMapping("/branch-id/{branchId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ForeclosureDto>> getForeclosureByBranch(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                            @Valid Pageable pageable,
                                                                      @PathVariable("branchId") long branchId,
                                                                      PagedResourcesAssembler assembler,
                                                                      JwtAuthenticationToken token,
                                                                      UriComponentsBuilder uriBuilder,
                                                                      final HttpServletResponse response);

    @GetMapping("/from-branch/{branchId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ForeclosureDto>> findAllByBranchIdIsNotContaining(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                                   @Valid Pageable pageable,
                                                                                @PathVariable("branchId") long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);
}

