package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import com.enatbanksc.casemanagementsystem.case_management.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @ManyToOne(fetch = FetchType.EAGER, optional = false )
    @JoinColumn(name = "appointmentId")
    @JsonIgnoreProperties(value={"expenseDetail"} )
    private JudicialAppointment judicialAppointment;


}
