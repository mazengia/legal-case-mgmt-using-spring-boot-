package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseDetilMapper {
    Expense toExpense(ExpenseDto expenseDto);
    ExpenseDto toExpenseDto(Expense expense);
}
