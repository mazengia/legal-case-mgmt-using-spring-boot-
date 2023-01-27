package com.enatbanksc.casemanagementsystem.case_management.machines;

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
@RequestMapping("/api/v1/machine")
@RequiredArgsConstructor
public class MachineController implements MachineApi {
    private final MachineService machineService;
    private final MachineMapper machineMapper;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public MachineDto createMachine(long mortgageId, MachineDto machineDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return machineMapper.toCaseTypeDto(machineService.machineType(mortgageId,machineMapper.toCaseType(machineDto), token));
    }

    @Override
    public MachineDto getMachineById(long caseTypeId) {
        return machineMapper.toCaseTypeDto(machineService.getCaseType(caseTypeId));
    }

    @Override
    public void deleteMachine(long caseTypeId) {
        machineService.deleteCaseType(caseTypeId);
    }

    @Override
    public MachineDto updateMachine(long caseTypeId, MachineDto machineDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return machineMapper.toCaseTypeDto(machineService.updateCaseType(caseTypeId, machineMapper.toCaseType(machineDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<MachineDto>> getMachine(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MachineDto.class, uriBuilder, response, pageable.getPageNumber(), machineService.getCaseTypes(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MachineDto>>(assembler.toModel(machineService.getCaseTypes(pageable, token).map(machineMapper::toCaseTypeDto)), HttpStatus.OK);
    }
}
