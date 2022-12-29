package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class ExpenseDetailDto extends Auditable {
    private Long expense_detail_id;
    private Double amount;
    //    private Expense expense;
    private String expense;
//    private Double miscellaneous;
    private Litigation litigation;
}
