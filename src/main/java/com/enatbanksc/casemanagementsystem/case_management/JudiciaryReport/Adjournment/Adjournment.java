package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

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
@Table(name = "adjournments")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE adjournments SET deleted = 'true' WHERE adjournment_id=? and version=?")
public class Adjournment extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adjournmentId;
    private LocalDateTime date;
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER, optional = false )
    @JoinColumn(name = "litigationId")
    @JsonIgnoreProperties(value={"adjournment"} )
    private Litigation litigation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;
}
