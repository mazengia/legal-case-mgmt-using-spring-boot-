package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import com.enatbanksc.casemanagementsystem.case_management.Intervene.InterveneDto;
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
@RequestMapping("/api/v1/foreClosure")
@RequiredArgsConstructor
public class ForeClosureController implements ForeClosureApi {
    private final ForeClosureService foreClosureService;
    private final ForeClosureMapper foreClosureMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ForeClosureDto createForeClosure(
            ForeClosureDto foreClosureDto
            , JwtAuthenticationToken token
    )
            throws IllegalAccessException {
        return foreClosureMapper.toForeClosureDto(
                foreClosureService.createForeClosure(
                        foreClosureMapper.toForeClosure(foreClosureDto)
                        , token
                ));
    }

    @Override
    public ForeClosureDto getForeClosure(long id) {
        return foreClosureMapper.toForeClosureDto(foreClosureService.getForeClosure(id));
    }

    @Override
    public ForeClosureDto updateForeClosure(long id, ForeClosureDto foreClosureDto
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        return foreClosureMapper.toForeClosureDto(
                foreClosureService.updateForeClosure(id, foreClosureMapper.toForeClosure(foreClosureDto)
                , token
        ));
    }

    @Override
    public ResponseEntity<PagedModel<ForeClosureDto>> getForeClosure(Pageable pageable, PagedResourcesAssembler assembler
            , JwtAuthenticationToken token
            , UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                InterveneDto.class, uriBuilder, response, pageable.getPageNumber(), foreClosureService.getForeClosure(pageable
                , token
        ).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ForeClosureDto>>(assembler.toModel(foreClosureService.getForeClosure(pageable
                , token
        ).map(foreClosureMapper::toForeClosureDto)), HttpStatus.OK);
    }
}
