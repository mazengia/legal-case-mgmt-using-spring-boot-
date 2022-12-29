package com.enatbanksc.casemanagementsystem.case_management.Executions;

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
@RequestMapping("/api/v1/execution")
@RequiredArgsConstructor
public class ExecutionsController implements ExecutionsApi {
    private final ExecutionsService executionsService;
    private final ExecutionsMapper executionsMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ExecutionsDto createLitigation(ExecutionsDto executionsDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return executionsMapper.toLitigationDto(executionsService.createLitigation(executionsMapper.toLitigation(executionsDto), token));
    }

    @Override
    public ExecutionsDto getLitigation(long id) {
        return executionsMapper.toLitigationDto(executionsService.getLitigation(id));
    }

    @Override
    public ExecutionsDto updateLitigation(long id, ExecutionsDto executionsDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return executionsMapper.toLitigationDto(executionsService.updateLitigation(id, executionsMapper.toLitigation(executionsDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> getLitigations(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.getLitigations(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.getLitigations(pageable).map(executionsMapper::toLitigationDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> getLitigationByCaseStage(Pageable pageable, CaseStage caseStage, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.getLitigationByCaseStage(pageable,caseStage, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.getLitigationByCaseStage(pageable,caseStage, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByBranchId(Pageable pageable, Long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.findLitigationByBranchId(pageable,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findLitigationByBranchId(pageable,branchId, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.findLitigationByAttorneyHandlingTheCase(pageable,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findLitigationByAttorneyHandlingTheCase(pageable,attorney, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByStatus(Pageable pageable, String status, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.findLitigationByStatus(pageable,status, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findLitigationByStatus(pageable,status, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilter(Pageable pageable, String filterValue , PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findLitigationByFilter(pageable,filterValue, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findLitigationByFilter(pageable,filterValue, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilterByBranch(Pageable pageable, String filterValue, Long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findLitigationByFilterByBranch(pageable,filterValue,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findLitigationByFilterByBranch(pageable,filterValue,branchId, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);


    }


    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilterByattorney(Pageable pageable, String filterValue, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findLitigationByFilterByattorney(pageable,filterValue,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findLitigationByFilterByattorney(pageable,filterValue,attorney, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findLitigationByFilterByStatus(Pageable pageable, String filterValue, String status, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findLitigationByFilterByStatus(pageable,filterValue,status, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findLitigationByFilterByStatus(pageable,filterValue,status, token).map(executionsMapper::toLitigationDto)), HttpStatus.OK);


    }
}
