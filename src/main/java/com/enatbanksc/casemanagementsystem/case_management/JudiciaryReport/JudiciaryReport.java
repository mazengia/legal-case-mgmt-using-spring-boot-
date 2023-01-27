package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "judiciary_reports")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE judiciary_reports SET deleted = 'true' WHERE report_id=? and version=?")
public class JudiciaryReport extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    private String courtAddress;
    private Double judicialFee;
    private LocalDateTime judicialAppointmentDate;
    private String adjournmentReason;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

    @ManyToOne
    private Litigation litigation;

}
