package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import com.enatbanksc.casemanagementsystem.case_management.Comment.Comment;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment.Adjournment;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class JudiciaryReportDto extends RepresentationModel<JudiciaryReportDto> {
    private Long reportId;
    private String courtAddress;
    private Double judicialFee;
    private LocalDateTime judicialAppointmentDate;
    private String adjournmentReason;
    private List<Adjournment> nextAppointment;
    private Employee maintained_by;
    @JsonBackReference
    private Litigation litigation;
    private List<Comment> comments;
}
