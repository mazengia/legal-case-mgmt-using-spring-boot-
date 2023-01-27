package com.enatbanksc.casemanagementsystem.case_management.Intervene;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.Address;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "intervenes")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE intervenes SET deleted = 'true' WHERE intervene_id=? and version=?")
public class Intervene extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interveneId;
    @NotEmpty(message = "First Name may not be Empty!")
    private String firstName;
    @NotEmpty(message = "Last Name may not be Empty!")
    private String lastName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "state", column = @Column(name = "state")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "subCity", column = @Column(name = "subCity")),
            @AttributeOverride(name = "woredaOrKebele", column = @Column(name = "woredaOrKebele")),
            @AttributeOverride(name = "houseNo", column = @Column(name = "houseNo"))
    })
    private Address interveneAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

}
