package com.enatbanksc.casemanagementsystem.case_management.Advocate;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "advocates")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE advocates SET deleted = 1 WHERE id=? and version=?")
public class Advocate extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advocateId;
    private String firstName;
    private String lastName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;
}
