package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

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
@RequestMapping("/api/v1/adjournment")
@RequiredArgsConstructor
public class AdjournmentController implements AdjournmentApi {
    private final AdjournmentService adjournmentService;
    private final AdjournmentMapper adjournmentMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public AdjournmentDto createAdjournment(
            AdjournmentDto adjournmentDto,
            JwtAuthenticationToken token
    )
            throws IllegalAccessException {
        return adjournmentMapper.toAdjournmentDto(
                adjournmentService.createAdjournment(
                        adjournmentMapper.toAdjournment(adjournmentDto),
                        token
                ));
    }

    @Override
    public AdjournmentDto getAdjournment(long id) {
        return adjournmentMapper.toAdjournmentDto(adjournmentService.getAdjournment(id));
    }

    @Override
    public AdjournmentDto updateAdjournment(long id,
                                            AdjournmentDto adjournmentDto,
                                            JwtAuthenticationToken token
    ) throws IllegalAccessException {
        return adjournmentMapper.toAdjournmentDto(adjournmentService.updateAdjournment(id,
                adjournmentMapper.toAdjournment(adjournmentDto),
                token
        ));
    }

    @Override
    public ResponseEntity<PagedModel<AdjournmentDto>> getAdjournment(Pageable pageable,
                                                                     PagedResourcesAssembler assembler
            , JwtAuthenticationToken token
            , UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AdjournmentDto.class, uriBuilder, response, pageable.getPageNumber(), adjournmentService.getAdjournment(pageable
                , token
        ).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AdjournmentDto>>(assembler.toModel(adjournmentService.getAdjournment(pageable
                , token
        ).map(adjournmentMapper::toAdjournmentDto)), HttpStatus.OK);
    }
}
