package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;

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
@RequestMapping("/api/v1/mortgage-detail")
@RequiredArgsConstructor
public class MortgageDetailController implements MortgageDetailApi {
    private final MortgageDetailService mortgageDetailService;
    private final MortgageDetailMapper mortgageDetailMapper;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public MortgageDetailDto createMortgageDetail(MortgageDetailDto mortgageDetailDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return mortgageDetailMapper.toMortgageDetailDto(mortgageDetailService.createMortgageDetail(mortgageDetailMapper.toMortgageDetail(mortgageDetailDto), token));
    }

    @Override
    public MortgageDetailDto getMortgageDetail(long mortgageDetailId) {
        return mortgageDetailMapper.toMortgageDetailDto(mortgageDetailService.getMortgageDetail(mortgageDetailId));
    }

    @Override
    public MortgageDetailDto updateMortgageDetail(long mortgageDetailId, MortgageDetailDto mortgageDetailDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return mortgageDetailMapper.toMortgageDetailDto(mortgageDetailService.updateMortgageDetail(mortgageDetailId, mortgageDetailMapper.toMortgageDetail(mortgageDetailDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<MortgageDetailDto>> getMortgageDetail(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MortgageDetailDto.class, uriBuilder, response, pageable.getPageNumber(), mortgageDetailService.getMortgageDetail(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MortgageDetailDto>>(assembler.toModel(mortgageDetailService.getMortgageDetail(pageable, token).map(mortgageDetailMapper::toMortgageDetailDto)), HttpStatus.OK);
    }
}
