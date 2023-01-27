package com.enatbanksc.casemanagementsystem.case_management.Appeal;

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
@RequestMapping("/api/v1/appeal")
@RequiredArgsConstructor
public class AppealController implements AppealApi {
    private final AppealService appealService;
    private final AppealMapper appealMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public AppealDto createAppeal(AppealDto appealDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return appealMapper.toAppealDto(appealService.createAppeal(appealMapper.toAppeal(appealDto), token));
    }

    @Override
    public AppealDto getAppealById(long id) {
        return appealMapper.toAppealDto(appealService.getAppealById(id));
    }

    @Override
    public void deleteAppealById(long id) {
        appealService.deleteAppealById(id);
    }

    @Override
    public ResponseEntity<PagedModel<AppealDto>> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable,String attorneyHandlingTheCase, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AppealDto.class, uriBuilder, response, pageable.getPageNumber(), appealService.findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorneyHandlingTheCase, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AppealDto>>(assembler.toModel(appealService.findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorneyHandlingTheCase, token).map(appealMapper::toAppealDto)), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PagedModel<AppealDto>> findAllByLitigationId(Pageable pageable,long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AppealDto.class, uriBuilder, response, pageable.getPageNumber(), appealService.findAllByLitigationId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AppealDto>>(assembler.toModel(appealService.findAllByLitigationId(pageable,id, token).map(appealMapper::toAppealDto)), HttpStatus.OK);
    }
    @Override
    public AppealDto updateAppeal(long id, AppealDto appealDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return appealMapper.toAppealDto(appealService.updateAppeal(id, appealMapper.toAppeal(appealDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<AppealDto>> getAllAppeal(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AppealDto.class, uriBuilder, response, pageable.getPageNumber(), appealService.getAllAppeal(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AppealDto>>(assembler.toModel(appealService.getAllAppeal(pageable, token).map(appealMapper::toAppealDto)), HttpStatus.OK);
    }
}
