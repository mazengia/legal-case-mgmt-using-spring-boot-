package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;
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
@RequestMapping("/api/v1/appeal-applicant-respondent")
@RequiredArgsConstructor
public class AppealApplicantRespondentController implements AppealApplicantRespondentApi {
    private final AppealApplicantRespondentMapper appealApplicantRespondentMapper;
    private final AppealApplicantRespondentService appealApplicantRespondentService;
private  final AppealApplicantRespondentRepository respondentRepository;
    private final ApplicationEventPublisher eventPublisher;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<AppealApplicantRespondent> createAppealApplicantRespondent(@RequestBody @Valid List<AppealApplicantRespondent> appealApplicantRespondents, JwtAuthenticationToken token) throws IllegalAccessException{
        return   respondentRepository.saveAll(appealApplicantRespondents);
    }
//    @Override
//    public AppealApplicantRespondentDto createAdvocate(AppealApplicantRespondentDto appealApplicantRespondentDto, JwtAuthenticationToken token) throws IllegalAccessException {
//        return appealApplicantRespondentMapper.toAdvocateDto(appealApplicantRespondentService.createAdvocate(appealApplicantRespondentMapper.toAdvocate(appealApplicantRespondentDto), token));
//    }

    @Override
    public AppealApplicantRespondentDto getAdvocate(long id) {
        return appealApplicantRespondentMapper.toAdvocateDto(appealApplicantRespondentService.getAdvocate(id));
    }

    @Override
    public AppealApplicantRespondentDto updateAdvocate(long id, AppealApplicantRespondentDto appealApplicantRespondentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return appealApplicantRespondentMapper.toAdvocateDto(appealApplicantRespondentService.updateAdvocate(id, appealApplicantRespondentMapper.toAdvocate(appealApplicantRespondentDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<AppealApplicantRespondentDto>> getAdvocates(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                AppealApplicantRespondentDto.class, uriBuilder, response, pageable.getPageNumber(), appealApplicantRespondentService.getAdvocates(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<AppealApplicantRespondentDto>>(assembler.toModel(appealApplicantRespondentService.getAdvocates(pageable, token).map(appealApplicantRespondentMapper::toAdvocateDto)), HttpStatus.OK);
    }
}
