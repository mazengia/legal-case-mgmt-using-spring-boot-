package com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene;


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


public interface InterveneApi {

    @PostMapping("/{litigationId}")
    @ResponseStatus(HttpStatus.CREATED)
    List<InterveneDto> createIntervenes(@PathVariable("litigationId") long litigationId, @RequestBody @Valid List<InterveneDto> interveneDtos, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    InterveneDto getIntervene(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    InterveneDto updateIntervene(@PathVariable("id") long id, @RequestBody @Valid InterveneDto interveneDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<InterveneDto>> getIntervenes(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                               @Valid Pageable pageable,
                                                               PagedResourcesAssembler assembler,
                                                               JwtAuthenticationToken token,
                                                               UriComponentsBuilder uriBuilder,
                                                               final HttpServletResponse response);
}
