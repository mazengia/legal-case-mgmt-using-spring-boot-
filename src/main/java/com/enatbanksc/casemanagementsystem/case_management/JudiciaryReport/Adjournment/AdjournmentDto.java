package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;


import java.time.LocalDateTime;

@Data
public class AdjournmentDto {
    private Long adjournmentId;
    private LocalDateTime date;
    private String reason;
    @JsonIgnore
    @JsonBackReference
    private JudiciaryReport judiciaryReport;
    private Employee maintained_by;
}
