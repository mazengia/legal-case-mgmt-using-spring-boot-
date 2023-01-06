package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "expense")
@Data
public class Expense extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expense_detail_id;
    private String amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "litigationId")
    @JsonIgnoreProperties(value = {"expense"})
    private Litigation litigation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executionsId")
    @JsonIgnoreProperties(value = {"expense"})
    private Executions executions;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "foreClosureId")
    @JsonIgnoreProperties(value = {"expense"})
    private MortgageDetail mortgageDetail;


}
