package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public interface ExpenseService {
//    ExpenseDetail createExpenseDetail(ExpenseDetail expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException;
    Expense getExpenseDetail(long id);
    Page<Expense> getExpensesDetailByForeclosure(Pageable pageable, JwtAuthenticationToken token);

    Page<Expense> getExpensesDetailByExecution(Pageable pageable, JwtAuthenticationToken token);

    Page<Expense> getExpensesDetailByLitigation(Pageable pageable, JwtAuthenticationToken token);

    Expense updateExpenseDetail(long id, Expense expense, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteExpenseDetail(long id, JwtAuthenticationToken token);
    Page<Expense> findExpenseDetailByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token);

    Page<Expense> findExpenseDetailByForeclosureId(Pageable pageable, long id, JwtAuthenticationToken token);

    Page<Expense> findExpenseDetailByExecutionId(Pageable pageable, long id, JwtAuthenticationToken token);

    Page<Expense> findAllByExecutionsAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

    Page<Expense> findAllByLitigationAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token);

//    Iterable<ExpenseDetail> createExpense(List<ExpenseDetail> expenseDetails, JwtAuthenticationToken token) throws IllegalAccessException;
}






