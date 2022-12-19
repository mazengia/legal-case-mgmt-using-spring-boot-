package com.enatbanksc.casemanagementsystem.case_management.Expense;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ExpenseDto extends Auditable {
    private Long expenseId;
    @NotEmpty(message = "Expense Name may not be empty")
    private String expenseName;
    private String expenseTypeColor;
    private Employee maintained_by;
}
