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
    public ExecutionsDto createExecutions(ExecutionsDto executionsDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return executionsMapper.toExecutionsDto(executionsService.createExecutions(executionsMapper.toExecutions(executionsDto), token));
    }

    @Override
    public ExecutionsDto getExecutionsById(long id) {
        return executionsMapper.toExecutionsDto(executionsService.getExecutionsById(id));
    }

    @Override
    public ExecutionsDto updateExecutions(long id, ExecutionsDto executionsDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return executionsMapper.toExecutionsDto(executionsService.updateExecutions(id, executionsMapper.toExecutions(executionsDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> getExecutions(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.getExecutions(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.getExecutions(pageable).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> getExecutionsByCaseStage(Pageable pageable, CaseStage caseStage, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.getExecutionsByCaseStage(pageable,caseStage, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.getExecutionsByCaseStage(pageable,caseStage, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findExecutionsByBranchId(Pageable pageable, Long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.findExecutionsByBranchId(pageable,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findExecutionsByBranchId(pageable,branchId, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findExecutionsByAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.findExecutionsByAttorneyHandlingTheCase(pageable,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findExecutionsByAttorneyHandlingTheCase(pageable,attorney, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findExecutionsByStatus(Pageable pageable, String status, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder, response, pageable.getPageNumber(), executionsService.findExecutionsByStatus(pageable,status, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findExecutionsByStatus(pageable,status, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findExecutionsByFilter(Pageable pageable, String filterValue , PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findExecutionsByFilter(pageable,filterValue, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findExecutionsByFilter(pageable,filterValue, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findExecutionsByFilterByBranch(Pageable pageable, String filterValue, Long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findExecutionsByFilterByBranch(pageable,filterValue,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findExecutionsByFilterByBranch(pageable,filterValue,branchId, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);


    }


    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findExecutionsByFilterByattorney(Pageable pageable, String filterValue, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findExecutionsByFilterByattorney(pageable,filterValue,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findExecutionsByFilterByattorney(pageable,filterValue,attorney, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<PagedModel<ExecutionsDto>> findExecutionsByFilterByStatus(Pageable pageable, String filterValue, String status, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExecutionsDto.class, uriBuilder,
                response, pageable.getPageNumber(), executionsService.findExecutionsByFilterByStatus(pageable,filterValue,status, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExecutionsDto>>(assembler.toModel(executionsService.findExecutionsByFilterByStatus(pageable,filterValue,status, token).map(executionsMapper::toExecutionsDto)), HttpStatus.OK);


    }
}
