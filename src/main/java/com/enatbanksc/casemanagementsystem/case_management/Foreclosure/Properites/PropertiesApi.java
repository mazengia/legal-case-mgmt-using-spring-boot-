package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;

import com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter.JudgmentCreditorGetter;
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

public interface PropertiesApi {
//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    Iterable<PropertiesDto>   createMortgageDetail(@RequestBody @Valid Iterable<PropertiesDto> mortgageDetailDto, JwtAuthenticationToken token) throws IllegalAccessException;
//


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    PropertiesDto getPropertiesById(@PathVariable("id") long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePropertiesById(@PathVariable("id") long id);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<PropertiesDto> updateProperties(@PathVariable("id") long id, @RequestBody @Valid List<PropertiesDto> mortgageDetailDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<PropertiesDto>> getAllProperties(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                               @Valid Pageable pageable,
                                                               PagedResourcesAssembler assembler,
                                                               JwtAuthenticationToken token,
                                                               UriComponentsBuilder uriBuilder,
                                                               final HttpServletResponse response);

    @GetMapping("/mortgage/{mortgageId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<PropertiesDto>> getPropertiesByMortgageDetailId(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                              @Valid Pageable pageable,
                                                                              @PathVariable("mortgageId") Long id,
                                                                              PagedResourcesAssembler assembler,
                                                                              JwtAuthenticationToken token,
                                                                              UriComponentsBuilder uriBuilder,
                                                                              final HttpServletResponse response);

}

