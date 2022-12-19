package com.enatbanksc.casemanagementsystem.case_management.Expense;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
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

public interface ExpenseApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ExpenseDto createExpense(@RequestBody @Valid ExpenseDto expenseDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping("/{expenseId}")
    @ResponseStatus(HttpStatus.OK)
    ExpenseDto getExpense(@PathVariable("expenseId") long expenseId);

    @PutMapping("/{expenseId}")
    @ResponseStatus(HttpStatus.OK)
    ExpenseDto updateExpense(@PathVariable("expenseId") long expenseId, @RequestBody @Valid ExpenseDto expenseDto, JwtAuthenticationToken token) throws IllegalAccessException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ExpenseDto>> getExpenses(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                            @Valid Pageable pageable,
                                                                            PagedResourcesAssembler assembler,
                                                                            JwtAuthenticationToken token,
                                                                            UriComponentsBuilder uriBuilder,
                                                                            final HttpServletResponse response);
}
