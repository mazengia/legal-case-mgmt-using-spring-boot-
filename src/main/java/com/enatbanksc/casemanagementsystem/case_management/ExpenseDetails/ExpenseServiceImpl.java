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
    public ExpenseDetail getExpenseDetail(long id) {
        return expenseDetailRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExpenseDetail.class, "ExpenseDetail Type with an id: " + id + " was not found!"));
    }

    @Override
    public Page<ExpenseDetail> getExpensesDetailByForeclosure(Pageable pageable, JwtAuthenticationToken token) {
        return expenseDetailRepository.findAllByMortgageDetailMortgageDetailIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(pageable);
    }
    @Override
    public Page<ExpenseDetail> getExpensesDetailByExecution(Pageable pageable, JwtAuthenticationToken token) {
        return expenseDetailRepository.findAllByExecutionsExecutionsIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(pageable);
    }
    @Override
    public Page<ExpenseDetail> getExpensesDetailByLitigation(Pageable pageable, JwtAuthenticationToken token) {
        return expenseDetailRepository.findAllByLitigationLitigationIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(pageable);
    }

    @Override
    public ExpenseDetail updateExpenseDetail(long id, ExpenseDetail expense, JwtAuthenticationToken token) throws IllegalAccessException {
        var et = getExpenseDetail(id);
        BeanUtils.copyProperties(expense, et, getNullPropertyNames(expense));
        return expenseDetailRepository.save(et);
    }

    @Override
    public void deleteExpenseDetail(long expenseId) {
        expenseDetailRepository.deleteById(expenseId);
    }
    @Override
    public Page<ExpenseDetail> findExpenseDetailByLitigationId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findExpenseDetailByLitigationLitigationIdAndDeletedIsFalseOrderByCreatedAtDesc(pageable,id);
    }

    @Override
    public Page<ExpenseDetail> findExpenseDetailByForeclosureId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findExpenseDetailByMortgageDetailMortgageDetailIdAndDeletedIsFalseOrderByCreatedAtDesc(pageable,id);
    }


    @Override
    public Page<ExpenseDetail> findExpenseDetailByExecutionId(Pageable pageable, long id, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findExpenseDetailByExecutionsExecutionsIdAndDeletedIsFalseOrderByCreatedAtDesc(pageable,id);
    }
    @Override
    public Page<ExpenseDetail> findAllByExecutionsAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findAllByExecutionsAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(pageable,attorney);
    }
    @Override
    public Page<ExpenseDetail> findAllByLitigationAttorneyHandlingTheCase(Pageable pageable, String attorney, JwtAuthenticationToken token) {
        return  expenseDetailRepository.findAllByLitigationAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(pageable,attorney);
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
