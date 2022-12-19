package com.enatbanksc.casemanagementsystem.case_management.Advocate;

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
@RequestMapping("/api/v1/advocates")
@RequiredArgsConstructor
public class AdvocateController implements AdvocateApi{
    private final AdvocateMapper advocateMapper;
    private final AdvocateService advocateService;

    private final ApplicationEventPublisher eventPublisher;
    @Override
    public AdvocateDto createAdvocate(AdvocateDto advocateDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return advocateMapper.toAdvocateDto(advocateService.createAdvocate(advocateMapper.toAdvocate(advocateDto), token));
    }

    @Override
    public AdvocateDto getAdvocate(long id) {
        return advocateMapper.toAdvocateDto(advocateService.getAdvocate(id));
    }

    @Override
    public AdvocateDto updateAdvocate(long id, AdvocateDto advocateDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return advocateMapper.toAdvocateDto(advocateService.updateAdvocate(id, advocateMapper.toAdvocate(advocateDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<AdvocateDto>> getAdvocates(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AdvocateDto.class, uriBuilder, response, pageable.getPageNumber(), advocateService.getAdvocates(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AdvocateDto>>(assembler.toModel(advocateService.getAdvocates(pageable, token).map(advocateMapper::toAdvocateDto)), HttpStatus.OK);
    }
}
