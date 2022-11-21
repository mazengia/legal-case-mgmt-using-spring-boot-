package com.enatbanksc.casemanagementsystem.case_management.settings.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.settings.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;

@Data
public class ExpenseDetailDto extends Auditable {
    private Long expense_detail_id;
    private Double amount;
    private Expense expense;
}
