package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
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
@RequestMapping("/api/v1/litigations")
@RequiredArgsConstructor
public class LitigationController implements LitigationApi{
    private final LitigationService litigationService;
    private final LitigationMapper litigationMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public LitigationDto createLitigation(LitigationDto litigationDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return litigationMapper.toLitigationDto(litigationService.createLitigation(litigationMapper.toLitigation(litigationDto), token));
    }

    @Override
    public LitigationDto getLitigation(long id) {
        return litigationMapper.toLitigationDto(litigationService.getLitigation(id));
    }

    @Override
    public LitigationDto updateLitigation(long id, LitigationDto litigationDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return litigationMapper.toLitigationDto(litigationService.updateLitigation(id, litigationMapper.toLitigation(litigationDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<LitigationDto>> getLitigations(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                LitigationDto.class, uriBuilder, response, pageable.getPageNumber(), litigationService.getLitigations(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.getLitigations(pageable).map(litigationMapper::toLitigationDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<LitigationDto>> getLitigationByCaseStage(Pageable pageable, CaseStage caseStage, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder, response, pageable.getPageNumber(), litigationService.getLitigationByCaseStage(pageable,caseStage, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.getLitigationByCaseStage(pageable,caseStage, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);


    }
}
