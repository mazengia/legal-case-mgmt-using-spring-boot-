package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

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
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseDetailRepository expenseDetailRepository;

    @Override
    public Expense getExpenseDetail(long id) {
        return expenseDetailRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Expense.class, "ExpenseDetail Type with an id: " + id + " was not found!"));
    }

    @Override
    public Page<Expense> getExpensesDetailByForeclosure(Pageable pageable, JwtAuthenticationToken token) {
        return expenseDetailRepository.findAllByMortgageDetailMortgageDetailIdNotNullOrderByCreatedAtDesc(pageable);
    }
    @Override
    public Page<Expense> getExpensesDetailByExecution(Pageable pageable, JwtAuthenticationToken token) {
        return expenseDetailRepository.findAllByExecutionsExecutionsIdNotNullOrderByCreatedAtDesc(pageable);
    }
    @Override
    public Page<Expense> getExpensesDetailByLitigation(Pageable pageable, JwtAuthenticationToken token) {
        return expenseDetailRepository.findAllByLitigationLitigationIdNotNullOrderByCreatedAtDesc(pageable);
    }

    @Override
    public Expense updateExpenseDetail(long id, Expense expense, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getExpenseDetail(id);
        BeanUtils.copyProperties(expense, et, getNullPropertyNames(expense));
        return expenseDetailRepository.save(et);
    }

    @Override
    public void deleteExpenseDetail(long expenseId, JwtAuthenticationToken token) {
        expenseDetailRepository.deleteById(expenseId);
    }
    @Override
    public Page<Expense> findExpenseDetailByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findExpenseDetailByLitigationLitigationIdOrderByCreatedAtDesc(pageable,id);
    }

    @Override
    public Page<Expense> findExpenseDetailByForeclosureId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findExpenseDetailByMortgageDetailMortgageDetailIdOrderByCreatedAtDesc(pageable,id);
    }


    @Override
    public Page<Expense> findExpenseDetailByExecutionId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findExpenseDetailByExecutionsExecutionsIdOrderByCreatedAtDesc(pageable,id);
    }
    @Override
    public Page<Expense> findAllByExecutionsAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findAllByExecutionsAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorney);
    }
    @Override
    public Page<Expense> findAllByLitigationAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(pageable,attorney);
    }

//    @Override
//    public Iterable<ExpenseDetail> createExpense(List<ExpenseDetail> expenseDetails, JwtAuthenticationToken token) throws IllegalAccessException {
//
////      for(ExpenseDetail expenseDetail:expenseDetails){
////          expenseDetailRepository.save(expenseDetail);
////      }
////         return null;
//        return (Iterable<ExpenseDetail>) expenseDetailRepository.saveAll(expenseDetails);
//
//    }
}
