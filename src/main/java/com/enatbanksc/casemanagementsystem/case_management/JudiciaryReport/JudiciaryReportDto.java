package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import com.enatbanksc.casemanagementsystem.case_management.Comment.Comment;
import com.enatbanksc.casemanagementsystem.case_management.Common.Address;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment.Adjournment;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Advocate.Advocate;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene.Intervene;
//import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class JudiciaryReportDto extends Auditable {
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
