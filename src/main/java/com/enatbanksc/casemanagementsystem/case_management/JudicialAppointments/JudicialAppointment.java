package com.enatbanksc.casemanagementsystem.case_management.JudicialAppointments;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "judicial_appointments")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE judicial_appointments SET deleted = 'true' WHERE appointment_id=? and version=?")
public class JudicialAppointment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private LocalDateTime appointmentDate;
    private String appointmentReason;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "litigationId")
    @JsonIgnoreProperties(value = {"judicialAppointment"})
    private Litigation litigation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executionsId")
    @JsonIgnoreProperties(value = {"judicialAppointment"})
    private Executions executions;


//    @OneToMany(mappedBy = "judicialAppointment")
//    private List<ExpenseDetail> expenseDetails;
}
