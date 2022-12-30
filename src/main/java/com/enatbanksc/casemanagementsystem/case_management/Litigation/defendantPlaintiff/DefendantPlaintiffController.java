package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/defendant-plaintiff")
@RequiredArgsConstructor
public class DefendantPlaintiffController implements DefendantPlaintiffApi {
    private final DefendantPlaintiffMapper defendantPlaintiffMapper;
    private final DefendantPlaintiffService defendantPlaintiffService;
    private final DefendantPlaintiffRepository respondentRepository;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<DefendantPlaintiff> createDefendantPlaintiff(@RequestBody @Valid List<DefendantPlaintiff> defendantPlaintiffs, JwtAuthenticationToken token) throws IllegalAccessException {
        return respondentRepository.saveAll(defendantPlaintiffs);
    }
//    @Override
//    public AppealApplicantRespondentDto createAdvocate(AppealApplicantRespondentDto appealApplicantRespondentDto, JwtAuthenticationToken token) throws IllegalAccessException {
//        return appealApplicantRespondentMapper.toAdvocateDto(appealApplicantRespondentService.createAdvocate(appealApplicantRespondentMapper.toAdvocate(appealApplicantRespondentDto), token));
//    }

    @Override
    public DefendantPlaintiffDto getAdvocate(long id) {
        return defendantPlaintiffMapper.toAdvocateDto(defendantPlaintiffService.getAdvocate(id));
    }

    @Override
    public DefendantPlaintiffDto updateAdvocate(long id, DefendantPlaintiffDto defendantPlaintiffDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return defendantPlaintiffMapper.toAdvocateDto(defendantPlaintiffService.updateAdvocate(id, defendantPlaintiffMapper.toAdvocate(defendantPlaintiffDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<DefendantPlaintiffDto>> getAdvocates(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                DefendantPlaintiffDto.class, uriBuilder, response, pageable.getPageNumber(), defendantPlaintiffService.getAdvocates(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<DefendantPlaintiffDto>>(assembler.toModel(defendantPlaintiffService.getAdvocates(pageable, token).map(defendantPlaintiffMapper::toAdvocateDto)), HttpStatus.OK);
    }
}