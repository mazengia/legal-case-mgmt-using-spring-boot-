package com.enatbanksc.casemanagementsystem.case_management.Executions;

import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
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

public interface ExecutionsApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ExecutionsDto createLitigation(@RequestBody @Valid ExecutionsDto executionsDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ExecutionsDto getLitigation(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ExecutionsDto updateLitigation(@PathVariable("id") long id, @RequestBody @Valid ExecutionsDto executionsDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ExecutionsDto>> getLitigations(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable,
                                                             PagedResourcesAssembler assembler,
                                                             UriComponentsBuilder uriBuilder,
                                                             final HttpServletResponse response);

    @GetMapping("/case-stage/{caseStage}")
    ResponseEntity<PagedModel<ExecutionsDto>> getLitigationByCaseStage(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                       @PathVariable("caseStage") CaseStage caseStage,
                                                                       PagedResourcesAssembler assembler,
                                                                       JwtAuthenticationToken token,
                                                                       UriComponentsBuilder uriBuilder,
                                                                       HttpServletResponse response);


    @GetMapping("/branch-id/{branchId}")
    ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByBranchId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                       @PathVariable("branchId") Long branchId,
                                                                       PagedResourcesAssembler assembler,
                                                                       JwtAuthenticationToken token,
                                                                       UriComponentsBuilder uriBuilder,
                                                                       HttpServletResponse response);

    @GetMapping("/attorney/{attorney}")
    ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByAttorneyHandlingTheCase(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                                      @PathVariable("attorney") String attorney,
                                                                                      PagedResourcesAssembler assembler,
                                                                                      JwtAuthenticationToken token,
                                                                                      UriComponentsBuilder uriBuilder,
                                                                                      HttpServletResponse response);

    @GetMapping("/status/{status}")
    ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByStatus(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                     @PathVariable("status") String status,
                                                                     PagedResourcesAssembler assembler,
                                                                     JwtAuthenticationToken token,
                                                                     UriComponentsBuilder uriBuilder,
                                                                     HttpServletResponse response);

    @GetMapping("/filter/{filterValue}")
    ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilter(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                     @PathVariable("filterValue") String filterValue,
                                                                     PagedResourcesAssembler assembler,
                                                                     JwtAuthenticationToken token,
                                                                     UriComponentsBuilder uriBuilder,
                                                                     HttpServletResponse response);

    @GetMapping("/filter/branch/{branchId}/{filterValue}")
    ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilterByBranch(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                             @PathVariable("filterValue") String filterValue,
                                                                             @PathVariable("branchId") Long branchId,
                                                                             PagedResourcesAssembler assembler,
                                                                             JwtAuthenticationToken token,
                                                                             UriComponentsBuilder uriBuilder,
                                                                             HttpServletResponse response);


    @GetMapping("/filter/attorney/{attorney}/{filterValue}")
    ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilterByattorney(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                               @PathVariable("filterValue") String filterValue,
                                                                               @PathVariable("attorney") String attorney,
                                                                               PagedResourcesAssembler assembler,
                                                                               JwtAuthenticationToken token,
                                                                               UriComponentsBuilder uriBuilder,
                                                                               HttpServletResponse response);

    @GetMapping("/filter/status/{status}/{filterValue}")
    ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilterByStatus(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                             @PathVariable("filterValue") String filterValue,
                                                                             @PathVariable("status") String status,
                                                                             PagedResourcesAssembler assembler,
                                                                             JwtAuthenticationToken token,
                                                                             UriComponentsBuilder uriBuilder,
                                                                             HttpServletResponse response);



}
