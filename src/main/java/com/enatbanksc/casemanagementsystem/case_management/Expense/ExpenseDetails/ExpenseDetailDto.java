package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class ExpenseDetailDto extends Auditable {
    private Long expense_detail_id;
    private Double amount;
    private String expense;
    private Litigation litigation;
    private Executions executions;
    private MortgageDetail mortgageDetail;
}
