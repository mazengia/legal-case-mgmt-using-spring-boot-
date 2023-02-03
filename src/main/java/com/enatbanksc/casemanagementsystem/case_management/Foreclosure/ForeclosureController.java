package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;

 import com.enatbanksc.casemanagementsystem.case_management.JasperReport.ReportService;
 import com.enatbanksc.casemanagementsystem.case_management._config.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
 import org.springframework.http.MediaType;
 import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
 import java.io.IOException;
 import java.io.OutputStream;

@RestController
@RequestMapping("/api/v1/foreclosure")
@RequiredArgsConstructor
public class ForeclosureController implements ForeclosureApi {
    private final ForeclosureService foreclosureService;
    private final ForeclosureMapper foreclosureMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final ReportService reportService;
    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public void foreclosureReportPdf(HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=".concat("by maze"));
            reportService.foreclosure("pdf", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ForeclosureDto createForeclosure(ForeclosureDto foreclosureDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return foreclosureMapper.toForeclosureDto(foreclosureService.createForeclosure(foreclosureMapper.toForeclosure(foreclosureDto), token));
    }
    @Override
    public ResponseEntity<PagedModel<ForeclosureDto>> findMortgageByAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ForeclosureDto.class, uriBuilder, response, pageable.getPageNumber(), foreclosureService.findMortgageByAttorneyHandlingTheCase(pageable,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ForeclosureDto>>(assembler.toModel(foreclosureService.findMortgageByAttorneyHandlingTheCase(pageable,attorney, token).map(foreclosureMapper::toForeclosureDto)), HttpStatus.OK);

    }

    @Override
    public ForeclosureDto getForeclosureById(long id) {
        return foreclosureMapper.toForeclosureDto(foreclosureService.getForeclosureById(id));
    }

    @Override
    public void deleteForeclosureById(long id) {
        foreclosureService.deleteForeclosureById(id);
    }

    @Override
    public ForeclosureDto updateForeclosureById(long id, ForeclosureDto foreclosureDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return foreclosureMapper.toForeclosureDto(foreclosureService.updateForeclosure(id, foreclosureMapper.toForeclosure(foreclosureDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<ForeclosureDto>> getAllForeclosure(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ForeclosureDto.class, uriBuilder, response, pageable.getPageNumber(), foreclosureService.getAllForeclosure(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ForeclosureDto>>(assembler.toModel(foreclosureService.getAllForeclosure(pageable, token).map(foreclosureMapper::toForeclosureDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<ForeclosureDto>> getForeclosureByStatus(Pageable pageable, String status, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ForeclosureDto.class, uriBuilder, response, pageable.getPageNumber(), foreclosureService.findForeclosureByStatus(pageable,status, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ForeclosureDto>>(assembler.toModel(foreclosureService.findForeclosureByStatus(pageable,status, token).map(foreclosureMapper::toForeclosureDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ForeclosureDto>> getForeclosureByBranch(Pageable pageable, long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ForeclosureDto.class, uriBuilder, response, pageable.getPageNumber(), foreclosureService.findForeclosureByBranchId(pageable,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ForeclosureDto>>(assembler.toModel(foreclosureService.findForeclosureByBranchId(pageable,branchId, token).map(foreclosureMapper::toForeclosureDto)), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<PagedModel<ForeclosureDto>> findAllByBranchIdIsNotContaining(Pageable pageable, long branchId, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ForeclosureDto.class, uriBuilder, response, pageable.getPageNumber(), foreclosureService.findAllByBranchIdIsNotContaining(pageable,branchId, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ForeclosureDto>>(assembler.toModel(foreclosureService.findAllByBranchIdIsNotContaining(pageable,branchId, token).map(foreclosureMapper::toForeclosureDto)), HttpStatus.OK);

    }


}
