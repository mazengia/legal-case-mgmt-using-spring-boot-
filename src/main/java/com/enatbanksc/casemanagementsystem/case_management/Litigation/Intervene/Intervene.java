package com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene;

import com.enatbanksc.casemanagementsystem.case_management.Common.Address;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Contact;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "intervenes")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE intervenes SET deleted = 1 WHERE id=? and version=?")
public class Intervene extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interveneId;

    @NotEmpty(message = "First Name may not be Empty!")
    private String firstName;
    @NotEmpty(message = "Last Name may not be Empty!")
    private String lastName;
    private Address interveneAddress;
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
