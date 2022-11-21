package com.enatbanksc.casemanagementsystem.case_management.settings.Expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;



@Service
public interface ExpenseService {
    Expense createExpense(Expense expense, JwtAuthenticationToken token) throws IllegalAccessException;
    Expense getExpense(long expenseId);
    Page<Expense> getExpenses(Pageable pageable, JwtAuthenticationToken token);
    Expense updateExpense(long expenseId, Expense expense, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteExpense(long expenseId, JwtAuthenticationToken token);

}






