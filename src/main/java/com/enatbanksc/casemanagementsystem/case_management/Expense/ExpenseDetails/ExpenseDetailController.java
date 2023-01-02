package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

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
@RequestMapping("/api/v1/expense-detail")
@RequiredArgsConstructor
public class ExpenseDetailController implements ExpenseDetailApi {
    private final ExpenseDetailService expenseDetailService;
    private final ExpenseDetailMapper expenseDetailMapper;
    private final ExpenseDetailRepository expenseDetailRepository;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createExpenseDetail(@RequestBody @Valid List<ExpenseDetail> expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException{
//        System.out.println("dfdf");
//        System.out.println(expenseDetail);
//        System.out.println("dfdf");
        return (ResponseEntity<?> )expenseDetailRepository.saveAll(expenseDetail);
    }
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<?> updateExpenseDetail(@PathVariable("id") long id, @RequestBody @Valid List<ExpenseDetail> expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException{
//        var et = getExpenseDetail(id);
//        BeanUtils.copyProperties(expenseDetail, et, getNullPropertyNames(expenseDetail));
//        return expenseDetailRepository.save(et);
//    }

//
//    @Override
//    public  ExpenseDetailDto createExpenseDetail( ExpenseDetailDto  expenseDetailDto, JwtAuthenticationToken token) throws IllegalAccessException {
//        return expenseDetailMapper.toExpenseDetailDto(expenseDetailService.createExpenseDetail(expenseDetailMapper.toExpenseDetail( expenseDetailDto), token));
//    }

    @Override
    public ExpenseDetailDto getExpenseDetail(long id) {
        return expenseDetailMapper.toExpenseDetailDto(expenseDetailService.getExpenseDetail(id));
    }

    @Override
    public ExpenseDetailDto updateExpenseDetail(long id, ExpenseDetailDto expenseDetailDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return expenseDetailMapper.toExpenseDetailDto(expenseDetailService.updateExpenseDetail(id, expenseDetailMapper.toExpenseDetail(expenseDetailDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>> getALLExpensesDetailByLitigation(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.getExpensesDetailByLitigation(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.getExpensesDetailByLitigation(pageable, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>>
    findExpenseDetailByLitigationId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.findExpenseDetailByLitigationId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.findExpenseDetailByLitigationId(pageable,id, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>>
    findExpenseDetailByForeClosureId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.findExpenseDetailByLitigationId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.findExpenseDetailByForeclosureId(pageable,id, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>>
    findExpenseDetailByExecutionId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.findExpenseDetailByLitigationId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.findExpenseDetailByExecutionId(pageable,id, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>> getALLExpensesDetailByExecution(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.getExpensesDetailByExecution(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.getExpensesDetailByExecution(pageable, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);
    }
    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>> getALLExpensesDetailByForeclosure(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.getExpensesDetailByForeclosure(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.getExpensesDetailByForeclosure(pageable, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);
    }

}
