package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class JudicialAppointmentDto extends RepresentationModel<JudicialAppointmentDto> {
    private Long appointmentId;
    private LocalDateTime appointmentDate;
    private String appointmentReason;
    private Employee maintained_by;
    private Litigation litigation;
    private Executions executions;
//    private List<ExpenseDetail> expenseDetails;
}
