package com.enatbanksc.casemanagementsystem.case_management.settings.Expense;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toExpense(ExpenseDto expenseDto);
    ExpenseDto toExpenseDto(Expense expense);
}
