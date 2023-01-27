package com.enatbanksc.casemanagementsystem.case_management.MortgageDetail;

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

public interface MortgageDetailApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    MortgageDetailDto createMortgageDetail(@RequestBody @Valid MortgageDetailDto mortgageDetailDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/attorney/{attorney}")
    ResponseEntity<PagedModel<MortgageDetailDto>> findMortgageByAttorneyHandlingTheCase(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                                        @PathVariable("attorney") String attorney,
                                                                                        PagedResourcesAssembler assembler,
                                                                                        JwtAuthenticationToken token,
                                                                                        UriComponentsBuilder uriBuilder,
                                                                                        HttpServletResponse response);

    @GetMapping("/{mortgageDetailId}")
    @ResponseStatus(HttpStatus.OK)
    MortgageDetailDto getMortgageDetailById(@PathVariable("mortgageDetailId") long mortgageDetailId);

    @DeleteMapping("/{mortgageDetailId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteMortgageDetailById(@PathVariable("mortgageDetailId") long mortgageDetailId);

    @PutMapping("/{mortgageDetailId}")
    @ResponseStatus(HttpStatus.OK)
    MortgageDetailDto updateMortgageDetail(@PathVariable("mortgageDetailId") long mortgageDetailId, @RequestBody @Valid MortgageDetailDto mortgageDetailDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MortgageDetailDto>> getAllMortgageDetail(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                       @Valid Pageable pageable,
                                                                       PagedResourcesAssembler assembler,
                                                                       JwtAuthenticationToken token,
                                                                       UriComponentsBuilder uriBuilder,
                                                                       final HttpServletResponse response);

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MortgageDetailDto>> getMortgageDetailByStatus(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                            @Valid Pageable pageable,
                                                                            @PathVariable("status") String status,
                                                                            PagedResourcesAssembler assembler,
                                                                            JwtAuthenticationToken token,
                                                                            UriComponentsBuilder uriBuilder,
                                                                            final HttpServletResponse response);

    @GetMapping("/branch-id/{branchId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MortgageDetailDto>> getMortgageDetailByBranch(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                            @Valid Pageable pageable,
                                                                            @PathVariable("branchId") long branchId,
                                                                            PagedResourcesAssembler assembler,
                                                                            JwtAuthenticationToken token,
                                                                            UriComponentsBuilder uriBuilder,
                                                                            final HttpServletResponse response);

    @GetMapping("/from-branch/{branchId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MortgageDetailDto>> findAllByBranchIdIsNotContaining(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                                   @Valid Pageable pageable,
                                                                                   @PathVariable("branchId") long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);
}

