package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdjournmentDto extends RepresentationModel<AdjournmentDto> {
    private Long adjournmentId;
    private LocalDateTime date;
    private String reason;
    private Litigation litigation;
    private Employee maintained_by;
}
