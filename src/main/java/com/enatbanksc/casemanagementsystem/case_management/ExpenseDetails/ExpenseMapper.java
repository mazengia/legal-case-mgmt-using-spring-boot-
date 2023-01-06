package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toExpenseDetail(ExpenseDto expenseDto);
    ExpenseDto toExpenseDetailDto(Expense expense);

    List<ExpenseDto> toDefendantPlaintiffDto(Iterable<Expense> expenseDetails
    );
    List<Expense> toDefendantPlaintiff(List<ExpenseDto> expenseDtos);
}
