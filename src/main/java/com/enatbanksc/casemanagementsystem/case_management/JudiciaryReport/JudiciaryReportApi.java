package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationDto;
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

public interface JudiciaryReportApi {
    @PostMapping("/{litigationId}")
    @ResponseStatus(HttpStatus.CREATED)
    JudiciaryReportDto createJudiciaryReport(@PathVariable("litigationId") long litigationId, @RequestBody @Valid JudiciaryReportDto judiciaryReportDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    JudiciaryReportDto getJudiciaryReport(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    JudiciaryReportDto updateJudiciaryReportDto(@PathVariable("id") long id, @RequestBody @Valid JudiciaryReportDto judiciaryReportDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<JudiciaryReportDto>> getJudiciaryReports(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                             @Valid Pageable pageable,
                                                             PagedResourcesAssembler assembler,
                                                             JwtAuthenticationToken token,
                                                             UriComponentsBuilder uriBuilder,
                                                             HttpServletResponse response);
}
