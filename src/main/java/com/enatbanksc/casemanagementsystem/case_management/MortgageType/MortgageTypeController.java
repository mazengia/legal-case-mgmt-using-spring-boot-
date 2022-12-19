package com.enatbanksc.casemanagementsystem.case_management.MortgageType;

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
@RequestMapping("/api/v1/mortgage-types")
@RequiredArgsConstructor
public class MortgageTypeController implements MortgageTypeApi{
    private final MortgageTypeService mortgageTypeService;
    private final MortgageTypeMapper mortgageTypeMapper;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public MortgageTypeDto createMortgageType(MortgageTypeDto mortgageTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return mortgageTypeMapper.toMortgageTypeDto(mortgageTypeService.createMortgageType(mortgageTypeMapper.toMortgageType(mortgageTypeDto), token));
    }

    @Override
    public MortgageTypeDto getMortgageType(long mortgageTypeId) {
        return mortgageTypeMapper.toMortgageTypeDto(mortgageTypeService.getMortgageType(mortgageTypeId));
    }

    @Override
    public MortgageTypeDto updateMortgageType(long mortgageTypeId, MortgageTypeDto mortgageTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return mortgageTypeMapper.toMortgageTypeDto(mortgageTypeService.updateMortgageType(mortgageTypeId, mortgageTypeMapper.toMortgageType(mortgageTypeDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<MortgageTypeDto>> getMortgageType(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MortgageTypeDto.class, uriBuilder, response, pageable.getPageNumber(), mortgageTypeService.getMortgageTypes(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MortgageTypeDto>>(assembler.toModel(mortgageTypeService.getMortgageTypes(pageable, token).map(mortgageTypeMapper::toMortgageTypeDto)), HttpStatus.OK);
    }
}
