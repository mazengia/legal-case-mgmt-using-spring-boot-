package com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LitigationEmployee {
    private String employeeId;
    private String fullName;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "email", column = @Column(name = "email"))
    })
    private Contact contact;
}
