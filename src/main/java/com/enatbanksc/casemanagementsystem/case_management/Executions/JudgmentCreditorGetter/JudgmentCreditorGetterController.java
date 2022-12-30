package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;
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
@RequestMapping("/api/v1/judgment-creditor-getter")
@RequiredArgsConstructor
public class JudgmentCreditorGetterController implements JudgmentCreditorGetterApi {
    private final JudgmentCreditorGetterMapper judgmentCreditorGetterMapper;
    private final JudgmentCreditorGetterService judgmentCreditorGetterService;
private  final JudgmentCreditorGetterRepository respondentRepository;
    private final ApplicationEventPublisher eventPublisher;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<JudgmentCreditorGetter> createJudgmentCreditorGetter(@RequestBody @Valid List<JudgmentCreditorGetter> judgmentCreditorGetters, JwtAuthenticationToken token) throws IllegalAccessException{
        return   respondentRepository.saveAll(judgmentCreditorGetters);
    }
//    @Override
//    public AppealApplicantRespondentDto createAdvocate(AppealApplicantRespondentDto appealApplicantRespondentDto, JwtAuthenticationToken token) throws IllegalAccessException {
//        return appealApplicantRespondentMapper.toAdvocateDto(appealApplicantRespondentService.createAdvocate(appealApplicantRespondentMapper.toAdvocate(appealApplicantRespondentDto), token));
//    }

    @Override
    public JudgmentCreditorGetterDro getAdvocate(long id) {
        return judgmentCreditorGetterMapper.toAdvocateDto(judgmentCreditorGetterService.getAdvocate(id));
    }

    @Override
    public JudgmentCreditorGetterDro updateAdvocate(long id, JudgmentCreditorGetterDro judgmentCreditorGetterDro, JwtAuthenticationToken token) throws IllegalAccessException {
        return judgmentCreditorGetterMapper.toAdvocateDto(judgmentCreditorGetterService.updateAdvocate(id, judgmentCreditorGetterMapper.toAdvocate(judgmentCreditorGetterDro), token));
    }

    @Override
    public ResponseEntity<PagedModel<JudgmentCreditorGetterDro>> getAdvocates(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                JudgmentCreditorGetterDro.class, uriBuilder, response, pageable.getPageNumber(), judgmentCreditorGetterService.getAdvocates(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudgmentCreditorGetterDro>>(assembler.toModel(judgmentCreditorGetterService.getAdvocates(pageable, token).map(judgmentCreditorGetterMapper::toAdvocateDto)), HttpStatus.OK);
    }
}
