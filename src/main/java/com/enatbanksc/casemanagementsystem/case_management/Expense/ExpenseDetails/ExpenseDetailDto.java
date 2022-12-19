package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class ExpenseDetailDto extends Auditable {
    private Long expense_detail_id;
    private Double amount;
    private Expense expense;
    private Double miscellaneous;
    private JudicialAppointment judicialAppointment;
}
