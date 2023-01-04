package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management.Files.AttachedFilesMapper;
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
    public LitigationDto createLitigation( LitigationDto litigationDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return litigationMapper.toLitigationDto(litigationService.createLitigation((litigationMapper.toLitigation(litigationDto)), token));

    }
    @Override
    public LitigationDto getLitigation(long id) {
        return litigationMapper.toLitigationDto(litigationService.getLitigation(id));
    }

    @Override
    public LitigationDto updateLitigation(long id, LitigationDto litigationDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return litigationMapper.toLitigationDto(litigationService.updateLitigation(id, litigationMapper.toLitigation( (litigationDto)), token));
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

    @Override
    public ResponseEntity<PagedModel<LitigationDto>> findLitigationByBranchId(Pageable pageable, Long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder, response, pageable.getPageNumber(), litigationService.findLitigationByBranchId(pageable,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.findLitigationByBranchId(pageable,branchId, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<LitigationDto>> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder, response, pageable.getPageNumber(), litigationService.findLitigationByAttorneyHandlingTheCase(pageable,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.findLitigationByAttorneyHandlingTheCase(pageable,attorney, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<PagedModel<LitigationDto>> findLitigationByStatus(Pageable pageable, String status, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder, response, pageable.getPageNumber(), litigationService.findLitigationByStatus(pageable,status, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.findLitigationByStatus(pageable,status, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<LitigationDto>> findLitigationByFilter(Pageable pageable, String filterValue , PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder,
                response, pageable.getPageNumber(), litigationService.findLitigationByFilter(pageable,filterValue, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.findLitigationByFilter(pageable,filterValue, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<LitigationDto>> findLitigationByFilterByBranch(Pageable pageable, String filterValue, Long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder,
                response, pageable.getPageNumber(), litigationService.findLitigationByFilterByBranch(pageable,filterValue,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.findLitigationByFilterByBranch(pageable,filterValue,branchId, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);


    }


    @Override
    public ResponseEntity<PagedModel<LitigationDto>> findLitigationByFilterByattorney(Pageable pageable, String filterValue, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder,
                response, pageable.getPageNumber(), litigationService.findLitigationByFilterByattorney(pageable,filterValue,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.findLitigationByFilterByattorney(pageable,filterValue,attorney, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<LitigationDto>> findLitigationByFilterByStatus(Pageable pageable, String filterValue, String status, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(LitigationDto.class, uriBuilder,
                response, pageable.getPageNumber(), litigationService.findLitigationByFilterByStatus(pageable,filterValue,status, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<LitigationDto>>(assembler.toModel(litigationService.findLitigationByFilterByStatus(pageable,filterValue,status, token).map(litigationMapper::toLitigationDto)), HttpStatus.OK);


    }
}
