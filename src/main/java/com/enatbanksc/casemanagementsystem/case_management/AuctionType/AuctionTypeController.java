package com.enatbanksc.casemanagementsystem.case_management.AuctionType;


import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auction-types")
@RequiredArgsConstructor
public class AuctionTypeController  implements AuctionTypeApi{
    private final AuctionTypeMapper auctionTypeMapper;
    private final AuctionTypeService auctionTypeService;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public AuctionTypeDto createAuctionType(AuctionTypeDto auctionTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return auctionTypeMapper.toAuctionTypeDto(auctionTypeService.createAuctionType(auctionTypeMapper.toAuctionType(auctionTypeDto), token));
    }

    @Override
    public AuctionTypeDto getAuctionType(long auctionTypeId) {
        return auctionTypeMapper.toAuctionTypeDto(auctionTypeService.getAuctionType(auctionTypeId));
    }

    @Override
    public AuctionTypeDto updateAuctionType(long auctionTypeId, AuctionTypeDto auctionTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return auctionTypeMapper.toAuctionTypeDto(auctionTypeService.updateAuctionType(auctionTypeId, auctionTypeMapper.toAuctionType(auctionTypeDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<AuctionTypeDto>> getAllAuctionTypes(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AuctionTypeDto.class, uriBuilder, response, pageable.getPageNumber(), auctionTypeService.getAuctionTypes(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AuctionTypeDto>>(assembler.toModel(auctionTypeService.getAuctionTypes(pageable, token).map(auctionTypeMapper::toAuctionTypeDto)), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PagedModel<AuctionTypeDto>> getAuctionTypesByMortgageDetail(Pageable pageable,long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AuctionTypeDto.class, uriBuilder, response, pageable.getPageNumber(), auctionTypeService.getAuctionTypesByMortgageDetail(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AuctionTypeDto>>(assembler.toModel(auctionTypeService.getAuctionTypesByMortgageDetail(pageable,id, token).map(auctionTypeMapper::toAuctionTypeDto)), HttpStatus.OK);
    }
}
