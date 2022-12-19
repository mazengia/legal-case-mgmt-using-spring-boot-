package com.enatbanksc.casemanagementsystem.case_management.Intervene;

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
@RequestMapping("/api/v1/intervenes")
@RequiredArgsConstructor
public class InterveneController implements InterveneApi{
    private final InterveneService interveneService;
    private final InterveneMapper interveneMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public InterveneDto createIntervenes(
                                         InterveneDto interveneDtos
            , JwtAuthenticationToken token
    )
            throws IllegalAccessException {
        return interveneMapper.toInterveneDto(
                interveneService.createIntervenes(
                        interveneMapper.toIntervene(interveneDtos)
                        , token
                ));
    }

    @Override
    public InterveneDto getIntervene(long id) {
        return interveneMapper.toInterveneDto(interveneService.getIntervene(id));
    }

    @Override
    public InterveneDto updateIntervene(long id, InterveneDto interveneDto
            , JwtAuthenticationToken token
    ) throws IllegalAccessException {
        return interveneMapper.toInterveneDto(interveneService.updateIntervene(id, interveneMapper.toIntervene(interveneDto)
                , token
        ));
    }

    @Override
    public ResponseEntity<PagedModel<InterveneDto>> getIntervenes(Pageable pageable, PagedResourcesAssembler assembler
            , JwtAuthenticationToken token
            , UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                InterveneDto.class, uriBuilder, response, pageable.getPageNumber(), interveneService.getIntervenes(pageable
                , token
        ).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<InterveneDto>>(assembler.toModel(interveneService.getIntervenes(pageable
                , token
        ).map(interveneMapper::toInterveneDto)), HttpStatus.OK);
    }
}
