package com.enatbanksc.casemanagementsystem.case_management.settings.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.settings.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management.settings.Expense.ExpenseDto;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "expense_details")
@Data
public class ExpenseDetail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expense_detail_id;
    private Double amount;
    @ManyToOne
    private Expense expense;
    private Double miscellaneous;

    @ManyToOne
    @MapsId("litigationId")
    private Litigation litigation;

    @ManyToOne
    @MapsId("reportId")
    @JsonBackReference(value="judiciaryReport")
    private JudiciaryReport judiciaryReport;
}
