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

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> createExpenseDetail(@RequestBody @Valid List<ExpenseDetail> expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException{
//        System.out.println("dfdf");
//        System.out.println(expenseDetail);
//        System.out.println("dfdf");
//        return (ResponseEntity<?> )expenseDetailRepository.saveAll(expenseDetail);
//    }


    @Override
    public  ExpenseDetailDto createExpenseDetail( ExpenseDetailDto  expenseDetailDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return expenseDetailMapper.toExpenseDetailDto(expenseDetailService.createExpenseDetail(expenseDetailMapper.toExpenseDetail( expenseDetailDto), token));
    }

    @Override
    public ExpenseDetailDto getExpenseDetail(long id) {
        return expenseDetailMapper.toExpenseDetailDto(expenseDetailService.getExpenseDetail(id));
    }

    @Override
    public ExpenseDetailDto updateExpenseDetail(long id, ExpenseDetailDto expenseDetailDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return expenseDetailMapper.toExpenseDetailDto(expenseDetailService.updateExpenseDetail(id, expenseDetailMapper.toExpenseDetail(expenseDetailDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>> getExpensesDetail(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.getExpensesDetail(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.getExpensesDetail(pageable, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<PagedModel<ExpenseDetailDto>>
    findExpenseDetailByLitigationId(Pageable pageable, long id, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(ExpenseDetailDto.class, uriBuilder, response, pageable.getPageNumber(), expenseDetailService.findExpenseDetailByLitigationId(pageable,id, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDetailDto>>(assembler.toModel(expenseDetailService.findExpenseDetailByLitigationId(pageable,id, token).map(expenseDetailMapper::toExpenseDetailDto)), HttpStatus.OK);

    }
}
