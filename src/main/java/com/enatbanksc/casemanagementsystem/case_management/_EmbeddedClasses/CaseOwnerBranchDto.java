package com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
