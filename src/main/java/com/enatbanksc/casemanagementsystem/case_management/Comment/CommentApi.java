package com.enatbanksc.casemanagementsystem.case_management.Comment;

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


public interface CommentApi{
    @PostMapping("/{litigationId}")
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto commentOnLitigation(@PathVariable("litigationId") long litigationId, @RequestBody @Valid CommentDto commentDto
            , JwtAuthenticationToken token
    ) throws IllegalAccessException;

    @PostMapping("/{reportId}")
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto commentOnJudiciaryReport(@PathVariable("reportId") long reportId, @RequestBody @Valid CommentDto commentDto

            , JwtAuthenticationToken token
    ) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CommentDto getComment(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CommentDto updateComment(@PathVariable("id") long id, @RequestBody @Valid CommentDto commentDto
            , JwtAuthenticationToken token
    ) throws IllegalAccessException;

    @GetMapping("/{litigationId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CommentDto>> getCommentsByLitigation(@PathVariable("litigationId")long litigationId,@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                       @Valid Pageable pageable,
                                                                       PagedResourcesAssembler assembler,
                                                                       JwtAuthenticationToken token,
                                                                       UriComponentsBuilder uriBuilder,
                                                                       HttpServletResponse response);

    @GetMapping("/{judiciaryReportId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CommentDto>> getCommentsByJudiciaryReportId(@PathVariable("judiciaryReportId")long judiciaryReportId, @Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
    @Valid Pageable pageable,
                                                                  PagedResourcesAssembler assembler,
                                                                  JwtAuthenticationToken token,
                                                                  UriComponentsBuilder uriBuilder,
                                                                  HttpServletResponse response);
}
