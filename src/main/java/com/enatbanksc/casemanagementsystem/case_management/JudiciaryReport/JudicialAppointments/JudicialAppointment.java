//package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;
//
//import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
//import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
//import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//import org.hibernate.annotations.SQLDelete;
//import org.hibernate.annotations.Where;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@Entity
//@Table(name = "judicial_appointments")
//@Data
//@Where(clause = "deleted=0")
//@SQLDelete(sql = "UPDATE judicial_appointments SET deleted = 1 WHERE id=? and version=?")
//public class JudicialAppointment extends Auditable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long appointmentId;
//
//    private LocalDateTime appointmentDate;
//    @JsonFormat(pattern = "HH:mm:ss")
//    private LocalTime localTime;
//    private String appointmentReason;
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
//            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
//            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
//            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
//    })
//    private Employee maintained_by;
//
//    @ManyToOne
//    private JudiciaryReport judiciaryReport;
//}
