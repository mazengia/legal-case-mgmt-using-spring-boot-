package com.enatbanksc.casemanagementsystem.case_management.Litigation.Advocate;

import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class AdvocateDto extends Auditable {
    private Long advocateId;
    private String firstName;
    private String lastName;
    private LocalDateTime date;
    @ManyToOne
    private JudiciaryReport judiciaryReport;
}
