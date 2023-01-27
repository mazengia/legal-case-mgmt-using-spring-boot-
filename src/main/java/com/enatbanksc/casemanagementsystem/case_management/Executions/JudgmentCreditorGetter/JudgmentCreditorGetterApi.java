package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

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
import java.util.List;

public interface JudgmentCreditorGetterApi {

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    AppealApplicantRespondentDto createAdvocate(@RequestBody @Valid AppealApplicantRespondentDto appealApplicantRespondentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    JudgmentCreditorGetterDto getJudgmentCreditorGetterById(@PathVariable("id") long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteJudgmentCreditorGetterById(@PathVariable("id") long id);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<JudgmentCreditorGetterDto>> getJudgmentCreditorGetter(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                           @Valid Pageable pageable,
                                                                                    PagedResourcesAssembler assembler,
                                                                                    JwtAuthenticationToken token,
                                                                                    UriComponentsBuilder uriBuilder,
                                                                                    final HttpServletResponse response);
    @GetMapping("/execution/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<JudgmentCreditorGetterDto>> getJudgmentCreditorGetterByExecutionId(@PathVariable("id") long id, Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response);
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<JudgmentCreditorGetterDto> updateJudgmentCreditorGetter(@PathVariable("id") long id, @RequestBody @Valid List<JudgmentCreditorGetterDto> judgmentCreditorGetterDtos, JwtAuthenticationToken token) throws IllegalAccessException;
}
