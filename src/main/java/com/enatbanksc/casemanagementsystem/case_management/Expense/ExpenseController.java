package com.enatbanksc.casemanagementsystem.case_management.Expense;

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
@RequestMapping("/api/v1/expense-types")
@RequiredArgsConstructor
public class ExpenseController implements ExpenseApi {
    private final ExpenseService expenseService;
    private final ExpenseMapper expenseMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return expenseMapper.toExpenseDto(expenseService.createExpense(expenseMapper.toExpense(expenseDto), token));
    }

    @Override
    public ExpenseDto getExpense(long expenseId) {
        return expenseMapper.toExpenseDto(expenseService.getExpense(expenseId));
    }

    @Override
    public ExpenseDto updateExpense(long expenseId, ExpenseDto expenseDto, JwtAuthenticationToken token) throws IllegalAccessException {
        return expenseMapper.toExpenseDto(expenseService.updateExpense(expenseId, expenseMapper.toExpense(expenseDto), token));
    }

    @Override
    public ResponseEntity<PagedModel<ExpenseDto>> getExpenses(Pageable pageable, PagedResourcesAssembler assembler, JwtAuthenticationToken token, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ExpenseDto.class, uriBuilder, response, pageable.getPageNumber(), expenseService.getExpenses(pageable, token).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ExpenseDto>>(assembler.toModel(expenseService.getExpenses(pageable, token).map(expenseMapper::toExpenseDto)), HttpStatus.OK);
    }
}
