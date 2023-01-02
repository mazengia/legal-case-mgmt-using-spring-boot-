package com.enatbanksc.casemanagementsystem.case_management.Files;

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

public interface AttachedFilesApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    AttachedFilesDto createFiles(@RequestBody @Valid AttachedFilesDto attachedFilesDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AttachedFilesDto getFilesById(@PathVariable("id") long id);

    @GetMapping("/attorney/{attorneyHandlingTheCase}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<AttachedFilesDto>> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(@Parameter(description = "pagination object", schema = @Schema(implementation = Pageable.class))
                                                                                                         @Valid Pageable pageable,
                                                                                                                @PathVariable("attorneyHandlingTheCase")   String attorneyHandlingTheCase,
                                                                                                                PagedResourcesAssembler assembler,
                                                                                                                JwtAuthenticationToken token,
                                                                                                                UriComponentsBuilder uriBuilder,
                                                                                                                final HttpServletResponse response);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    AttachedFilesDto updateAppeal(@PathVariable("id") long expenseId, @RequestBody @Valid AttachedFilesDto attachedFilesDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<AttachedFilesDto>> getAllAppeal(@Parameter(description = "pagination object", schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable,
                                                              PagedResourcesAssembler assembler,
                                                              JwtAuthenticationToken token,
                                                              UriComponentsBuilder uriBuilder,
                                                              final HttpServletResponse response);
}
