package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class ExpenseDto extends Auditable {
    private Long expense_detail_id;
    private String amount;
    private Litigation litigation;
    private Executions executions;
    private MortgageDetail mortgageDetail;
}
