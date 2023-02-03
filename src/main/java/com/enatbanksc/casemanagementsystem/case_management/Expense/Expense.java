package com.enatbanksc.casemanagementsystem.case_management.Expense;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "expense")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE expense SET deleted = 'true' WHERE expense_id=? and version=?")
public class Expense extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    private String amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "litigationId",nullable =true)
    @JsonIgnoreProperties(value = {"expense"})
    private Litigation litigation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executionsId",nullable =true)
    @JsonIgnoreProperties(value = {"expense"})
    private Executions executions;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "foreClosureId",nullable =true)
    @JsonIgnoreProperties(value = {"expense"})
    private Foreclosure foreclosure;


}
