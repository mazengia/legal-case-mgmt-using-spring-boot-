package com.enatbanksc.casemanagementsystem.case_management.settings.Expense;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.settings.Expense.ExpenseDetails.ExpenseDetail;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ExpenseDto extends Auditable {
    private Long expenseId;
    @NotEmpty(message = "Expense Name may not be empty")
    private String expenseName;
    private String expenseTypeColor;
    private Employee maintained_by;
}
