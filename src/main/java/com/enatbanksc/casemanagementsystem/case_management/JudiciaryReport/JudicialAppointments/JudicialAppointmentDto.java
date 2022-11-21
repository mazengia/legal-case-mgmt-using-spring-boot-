//package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;
//
//import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
//import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//
//import javax.persistence.ManyToOne;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@Data
//public class JudicialAppointmentDto extends Auditable {
//    private Long appointmentId;
//    private LocalDateTime appointmentDate;
//    @JsonFormat(pattern = "HH:mm:ss")
//    private LocalTime localTime;
//    private String appointmentReason;
//    private JudiciaryReport judiciaryReport;
//}
