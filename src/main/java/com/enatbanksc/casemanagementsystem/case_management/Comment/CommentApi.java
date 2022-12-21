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
    @GetMapping("/litigation-id/{id}")
    ResponseEntity<PagedModel<CommentDto>> getCommentByLitigationId(@Parameter(
            description = "pagination object",
            schema = @Schema(implementation = Pageable.class)) @Valid Pageable pageable,
                                                                                        @PathVariable("id") long id,
                                                                                        PagedResourcesAssembler assembler,
                                                                                        JwtAuthenticationToken token,
                                                                                        UriComponentsBuilder uriBuilder,
                                                                                        HttpServletResponse response);


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto createComment(@RequestBody @Valid CommentDto commentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CommentDto getCommentById(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CommentDto updateComment(@PathVariable("id") long id, @RequestBody @Valid CommentDto commentDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CommentDto>> getComment(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                         @Valid Pageable pageable,
                                                         PagedResourcesAssembler assembler,
                                                         JwtAuthenticationToken token,
                                                         UriComponentsBuilder uriBuilder,
                                                         final HttpServletResponse response);

   }
