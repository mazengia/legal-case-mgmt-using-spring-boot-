package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseDetail toExpenseDetail(ExpenseDto expenseDto);
    ExpenseDto toExpenseDetailDto(ExpenseDetail expense);

    List<ExpenseDto> toDefendantPlaintiffDto(Iterable<ExpenseDetail> expenseDetails
    );
    List<ExpenseDetail> toDefendantPlaintiff(List<ExpenseDto> expenseDtos);
}
