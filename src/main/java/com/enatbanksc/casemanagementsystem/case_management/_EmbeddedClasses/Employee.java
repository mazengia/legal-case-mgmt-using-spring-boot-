package com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String employeeId;
    private String email;
    private String fullName;
    @Embedded()
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "branch_code"))
            , @AttributeOverride(name = "name", column = @Column(name = "branch_name"))
            , @AttributeOverride(name = "id", column = @Column(name = "branch_id"))})
    private Branch branch;
}
