package com.enatbanksc.casemanagementsystem.case_management.machines;

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

public interface MachineApi {
    @PostMapping("/{mortgageId}")
    @ResponseStatus(HttpStatus.CREATED)
    MachineDto createMachine(@PathVariable("mortgageId") long mortgageId, @RequestBody @Valid MachineDto machineDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{caseTypeId}")
    @ResponseStatus(HttpStatus.OK)
    MachineDto getMachineById(@PathVariable("caseTypeId") long caseTypeId);
    @DeleteMapping("/{caseTypeId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteMachine(@PathVariable("caseTypeId") long caseTypeId);

    @PutMapping("/{caseTypeId}")
    @ResponseStatus(HttpStatus.OK)
    MachineDto updateMachine(@PathVariable("caseTypeId") long caseTypeId, @RequestBody @Valid MachineDto machineDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MachineDto>> getMachine(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable,
                                                      PagedResourcesAssembler assembler,
                                                      JwtAuthenticationToken token,
                                                      UriComponentsBuilder uriBuilder,
                                                      final HttpServletResponse response);
}
