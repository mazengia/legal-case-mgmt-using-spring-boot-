package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import javax.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "expenseDetail")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE expense_detail SET deleted = 'true' WHERE expense_detail_id=? and version=?")
public class ExpenseDetail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expense_detail_id;
    private String amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "litigationId",nullable =true)
    @JsonIgnoreProperties(value = {"expenseDetail"})
    private Litigation litigation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executionsId",nullable =true)
    @JsonIgnoreProperties(value = {"expenseDetail"})
    private Executions executions;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "foreClosureId",nullable =true)
    @JsonIgnoreProperties(value = {"expenseDetail"})
    private MortgageDetail mortgageDetail;


}
