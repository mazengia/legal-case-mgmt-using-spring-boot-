package com.enatbanksc.casemanagementsystem.case_management.Files;

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
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class AttachedFilesController implements AttachedFilesApi {
    private final AttachedFilesService attachedFilesService;
    private final AttachedFilesMapper attachedFilesMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public AttachedFilesDto createFiles(AttachedFilesDto attachedFilesDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return attachedFilesMapper.toFilesDto(attachedFilesService.createFiles(attachedFilesMapper.toFiles(attachedFilesDto), token));
    }

    @Override
    public AttachedFilesDto getFilesById(long id) {
        return attachedFilesMapper.toFilesDto(attachedFilesService.getFilesById(id));
    }
    @Override
    public ResponseEntity<PagedModel<AttachedFilesDto>> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorneyHandlingTheCase, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AttachedFilesDto.class, uriBuilder, response, pageable.getPageNumber(), attachedFilesService.findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorneyHandlingTheCase, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AttachedFilesDto>>(assembler.toModel(attachedFilesService.findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorneyHandlingTheCase, token).map(attachedFilesMapper::toFilesDto)), HttpStatus.OK);
    }

    @Override
    public AttachedFilesDto updateAppeal(long id, AttachedFilesDto attachedFilesDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return attachedFilesMapper.toFilesDto(attachedFilesService.updateFiles(id, attachedFilesMapper.toFiles(attachedFilesDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<AttachedFilesDto>> getAllAppeal(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AttachedFilesDto.class, uriBuilder, response, pageable.getPageNumber(), attachedFilesService.getAllFiles(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AttachedFilesDto>>(assembler.toModel(attachedFilesService.getAllFiles(pageable, token).map(attachedFilesMapper::toFilesDto)), HttpStatus.OK);
    }
}
