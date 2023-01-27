package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

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
@RequestMapping("/api/v1/judiciary-reports")
@RequiredArgsConstructor
public class JudiciaryReportController implements JudiciaryReportApi{
    private final JudiciaryReportService judiciaryReportService;
    private final JudiciaryReportMapper judiciaryReportMapper;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public JudiciaryReportDto createJudiciaryReport( JudiciaryReportDto judiciaryReportDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return judiciaryReportMapper.toJudiciaryReportDto(judiciaryReportService.createJudiciaryReport(judiciaryReportMapper.toJudiciaryReport(judiciaryReportDto), token));
    }

    @Override
    public JudiciaryReportDto getJudiciaryReport(long id) {
        return judiciaryReportMapper.toJudiciaryReportDto(judiciaryReportService.getJudiciaryReport(id));
    }

    @Override
    public void deleteJudiciaryReport(long id) {
        judiciaryReportService.deleteJudiciaryReport(id);
    }

    @Override
    public JudiciaryReportDto updateJudiciaryReportDto(long id, JudiciaryReportDto judiciaryReportDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return judiciaryReportMapper.toJudiciaryReportDto(judiciaryReportService.updateJudiciaryReport(id, judiciaryReportMapper.toJudiciaryReport(judiciaryReportDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<JudiciaryReportDto>> getJudiciaryReports(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder,  HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                JudiciaryReportDto.class, uriBuilder, response, pageable.getPageNumber(), judiciaryReportService.getJudiciaryReports(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudiciaryReportDto>>(assembler.toModel(judiciaryReportService.getJudiciaryReports(pageable, token).map(judiciaryReportMapper::toJudiciaryReportDto)), HttpStatus.OK);
    }
}
