package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management._exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.enatbanksc.casemanagementsystem.case_management._config.utils.Util.getNullPropertyNames;


@Service
@RequiredArgsConstructor
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
    private final ExpenseDetailRepository expenseDetailRepository;
    @Override
    public ExpenseDetail createExpenseDetail(ExpenseDetail expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException {

        return expenseDetailRepository.save(expenseDetail);
    }

    @Override
    public ExpenseDetail getExpenseDetail(long id) {
        return expenseDetailRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Expense.class, "ExpenseDetail Type with an id: " + id + " was not found!"));
    }

    @Override
    public Page<ExpenseDetail> getExpensesDetail(Pageable pageable, JwtAuthenticationToken token) {
        return expenseDetailRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public ExpenseDetail updateExpenseDetail(long id, ExpenseDetail expenseDetail, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getExpenseDetail(id);
        BeanUtils.copyProperties(expenseDetail, et, getNullPropertyNames(expenseDetail));
        return expenseDetailRepository.save(et);
    }

    @Override
    public void deleteExpenseDetail(long expenseId, JwtAuthenticationToken token) {
        expenseDetailRepository.deleteById(expenseId);
    }
    @Override
    public Page<ExpenseDetail> findExpenseDetailByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findExpenseDetailByLitigationLitigationIdOrderByCreatedAtDesc(pageable,id);
    }
}
