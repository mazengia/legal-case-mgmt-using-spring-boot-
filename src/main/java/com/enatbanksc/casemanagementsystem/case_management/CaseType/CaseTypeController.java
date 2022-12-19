package com.enatbanksc.casemanagementsystem.case_management.CaseType;

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
@RequestMapping("/api/v1/case-types")
@RequiredArgsConstructor
public class CaseTypeController implements CaseTypeApi{
    private final CaseTypeService caseTypeService;
    private final CaseTypeMapper caseTypeMapper;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public CaseTypeDto createCaseType(CaseTypeDto caseTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return caseTypeMapper.toCaseTypeDto(caseTypeService.createCaseType(caseTypeMapper.toCaseType(caseTypeDto), token));
    }

    @Override
    public CaseTypeDto getCaseType(long caseTypeId) {
        return caseTypeMapper.toCaseTypeDto(caseTypeService.getCaseType(caseTypeId));
    }

    @Override
    public CaseTypeDto updateCaseType(long caseTypeId, CaseTypeDto caseTypeDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return caseTypeMapper.toCaseTypeDto(caseTypeService.updateCaseType(caseTypeId, caseTypeMapper.toCaseType(caseTypeDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<CaseTypeDto>> getCaseTypes(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                CaseTypeDto.class, uriBuilder, response, pageable.getPageNumber(), caseTypeService.getCaseTypes(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CaseTypeDto>>(assembler.toModel(caseTypeService.getCaseTypes(pageable, token).map(caseTypeMapper::toCaseTypeDto)), HttpStatus.OK);
    }
}
