package com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CaseOwnerBranchDto {
    private Long id;
    private String code;
    private String name;
}
