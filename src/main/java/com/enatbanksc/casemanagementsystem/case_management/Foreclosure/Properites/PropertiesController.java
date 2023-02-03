package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;

import com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter.JudgmentCreditorGetter;
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
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertiesController implements PropertiesApi {
    private final PropertiesService propertiesService;
    private final PropertiesMapper propertiesMapper;
    private final PropertiesRepository propertiesRepository;
    private final ApplicationEventPublisher eventPublisher;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Properties> createProperties(@RequestBody @Valid List<Properties> properties, JwtAuthenticationToken token) throws IllegalAccessException {
        return propertiesRepository.saveAll(properties);
    }

//    @Override
//    public Iterable<PropertiesDto> createMortgageDetail(Iterable<PropertiesDto> propertiesDto, JwtAuthenticationToken token) throws IllegalAccessException {
//        return propertiesMapper.toPropertiesArrayDto(propertiesService.createProperties((List<Properties>) propertiesMapper.toPropertiesArray(propertiesDto), token));
//    }


    @Override
    public PropertiesDto getPropertiesById(long id) {
        return propertiesMapper.toPropertiesDto(propertiesService.getPropertiesById(id));
    }

    @Override
    public void deletePropertiesById(long id) {
        propertiesService.deletePropertiesById(id);
    }

    @Override
    public List<PropertiesDto> updateProperties(long id, List<PropertiesDto> mortgageDetailDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return propertiesMapper.toPropertiesDtoList(propertiesService.updateProperties(id, propertiesMapper.toPropertiesList(mortgageDetailDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<PropertiesDto>> getAllProperties(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                PropertiesDto.class, uriBuilder, response, pageable.getPageNumber(), propertiesService.getAllProperties(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<PropertiesDto>>(assembler.toModel(propertiesService.getAllProperties(pageable).map(propertiesMapper::toPropertiesDto)), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<PagedModel<PropertiesDto>> getPropertiesByMortgageDetailId(Pageable pageable, Long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                PropertiesDto.class, uriBuilder, response, pageable.getPageNumber(), propertiesService.getPropertiesByMortgageDetailId(pageable, id).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<PropertiesDto>>(assembler.toModel(propertiesService.getPropertiesByMortgageDetailId(pageable, id).map(propertiesMapper::toPropertiesDto)), HttpStatus.OK);

    }


}
