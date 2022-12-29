package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public interface ExpenseDetailService {
//    ExpenseDetail createExpenseDetail(ExpenseDetail expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException;
    ExpenseDetail getExpenseDetail(long id);
    Page<ExpenseDetail> getExpensesDetail(Pageable pageable, JwtAuthenticationToken token);
    ExpenseDetail updateExpenseDetail(long id, ExpenseDetail expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteExpenseDetail(long id, JwtAuthenticationToken token);
    Page<ExpenseDetail> findExpenseDetailByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token);

}






