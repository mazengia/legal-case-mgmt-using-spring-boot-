package com.enatbanksc.casemanagementsystem.case_management.Expense;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff.DefendantPlaintiff;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff.DefendantPlaintiffDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toExpense(ExpenseDto expenseDto);
    ExpenseDto toExpenseDto(Expense expense);


}
