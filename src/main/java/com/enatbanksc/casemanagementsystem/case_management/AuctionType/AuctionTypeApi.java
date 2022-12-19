package com.enatbanksc.casemanagementsystem.case_management.AuctionType;

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

public interface AuctionTypeApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    AuctionTypeDto createAuctionType(@RequestBody @Valid AuctionTypeDto auctionTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{auctionTypeId}")
    @ResponseStatus(HttpStatus.OK)
    AuctionTypeDto getAuctionType(@PathVariable("auctionTypeId") long auctionTypeId);

    @PutMapping("/{auctionTypeId}")
    @ResponseStatus(HttpStatus.OK)
    AuctionTypeDto updateAuctionType(@PathVariable("auctionTypeId") long auctionTypeId, @RequestBody @Valid AuctionTypeDto auctionTypeDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<AuctionTypeDto>> getAuctionTypes(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable,
                                                       PagedResourcesAssembler assembler,
                                                       JwtAuthenticationToken token,
                                                       UriComponentsBuilder uriBuilder,
                                                       final HttpServletResponse response);
}
