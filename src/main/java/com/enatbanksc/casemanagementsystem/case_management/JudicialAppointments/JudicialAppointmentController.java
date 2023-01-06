package com.enatbanksc.casemanagementsystem.case_management.JudicialAppointments;

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
@RequestMapping("/api/v1/judicial-appointments")
@RequiredArgsConstructor
public class JudicialAppointmentController implements JudicialAppointmentApi{
    private final JudicialAppointmentService judicialAppointmentService;
    private final JudicialAppointmentMapper judicialAppointmentMapper;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public JudicialAppointmentDto createJudiciaryAppointment(JudicialAppointmentDto judicialAppointmentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return judicialAppointmentMapper.toJudicialAppointmentDto(judicialAppointmentService.createJudicialAppointment(judicialAppointmentMapper.toJudicialAppointment(judicialAppointmentDto), token));
    }

    @Override
    public JudicialAppointmentDto getJudiciaryAppointmentById(long id) {
        return judicialAppointmentMapper.toJudicialAppointmentDto(judicialAppointmentService.getJudicialAppointment(id));
    }




    @Override
    public JudicialAppointmentDto updateJudiciaryAppointment(long id, JudicialAppointmentDto judicialAppointmentDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return judicialAppointmentMapper.toJudicialAppointmentDto(judicialAppointmentService.updateJudicialAppointment(id, judicialAppointmentMapper.toJudicialAppointment(judicialAppointmentDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getAllJudiciaryAppointment(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                JudicialAppointmentDto.class, uriBuilder, response, pageable.getPageNumber(), judicialAppointmentService.getJudicialAppointments(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudicialAppointmentDto>>(assembler.toModel(judicialAppointmentService.getJudicialAppointments(pageable, token).map(judicialAppointmentMapper::toJudicialAppointmentDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getJudicialAppointmentDtoByLitigationId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(JudicialAppointmentDto.class, uriBuilder, response, pageable.getPageNumber(), judicialAppointmentService.getJudiciaryReportByLitigationId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudicialAppointmentDto>>(assembler.toModel(judicialAppointmentService.getJudiciaryReportByLitigationId(pageable,id, token).map(judicialAppointmentMapper::toJudicialAppointmentDto)), HttpStatus.OK);

    }



    @Override
    public ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getJudicialAppointmentDtoByExecutionId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(JudicialAppointmentDto.class, uriBuilder, response, pageable.getPageNumber(), judicialAppointmentService.getAppointmentByExecutionId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudicialAppointmentDto>>(assembler.toModel(judicialAppointmentService.getAppointmentByExecutionId(pageable,id, token).map(judicialAppointmentMapper::toJudicialAppointmentDto)), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getJudicialAppointmentByExecutionsAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(JudicialAppointmentDto.class, uriBuilder, response, pageable.getPageNumber(), judicialAppointmentService.getAppointmentByExecutionsAttorneyHandlingTheCase(pageable,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudicialAppointmentDto>>(assembler.toModel(judicialAppointmentService.getAppointmentByExecutionsAttorneyHandlingTheCase(pageable,attorney, token).map(judicialAppointmentMapper::toJudicialAppointmentDto)), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<PagedModel<JudicialAppointmentDto>>
    getJudiciaryAppointmentByLitigationAttorneyHandlingTheCase(Pageable pageable, String attorney, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(JudicialAppointmentDto.class, uriBuilder, response, pageable.getPageNumber(), judicialAppointmentService.getAppointmentByLitigationAttorneyHandlingTheCase(pageable,attorney, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudicialAppointmentDto>>(assembler.toModel(judicialAppointmentService.getAppointmentByLitigationAttorneyHandlingTheCase(pageable,attorney, token).map(judicialAppointmentMapper::toJudicialAppointmentDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<JudicialAppointmentDto>> getALLAppointmentByExecution(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                JudicialAppointmentDto.class, uriBuilder, response, pageable.getPageNumber(), judicialAppointmentService.getAppointmentByExecution(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudicialAppointmentDto>>(assembler.toModel(judicialAppointmentService.getAppointmentByExecution(pageable, token).map(judicialAppointmentMapper::toJudicialAppointmentDto)), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PagedModel<JudicialAppointmentDto>> getALLAppointmentByLitigation(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                JudicialAppointmentDto.class, uriBuilder, response, pageable.getPageNumber(), judicialAppointmentService.getAppointmentByLitigation(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<JudicialAppointmentDto>>(assembler.toModel(judicialAppointmentService.getAppointmentByLitigation(pageable, token).map(judicialAppointmentMapper::toJudicialAppointmentDto)), HttpStatus.OK);
    }
}
