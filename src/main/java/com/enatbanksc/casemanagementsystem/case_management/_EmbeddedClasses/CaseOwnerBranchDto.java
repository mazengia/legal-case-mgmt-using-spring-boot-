package com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaseOwnerBranchDto {
    private Long id;
    private String code;
    private String name;
}
