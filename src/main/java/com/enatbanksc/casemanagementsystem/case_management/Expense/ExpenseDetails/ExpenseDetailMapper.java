package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseDetailMapper {
    ExpenseDetail toExpenseDetail(ExpenseDetailDto expenseDetailDto);
    ExpenseDetailDto toExpenseDetailDto(ExpenseDetail expenseDetail);

    List<ExpenseDetailDto> toDefendantPlaintiffDto(Iterable<ExpenseDetail> expenseDetails
    );
    List<ExpenseDetail> toDefendantPlaintiff( List<ExpenseDetailDto> expenseDetailDtos);
}
