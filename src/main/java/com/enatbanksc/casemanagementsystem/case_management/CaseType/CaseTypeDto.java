package com.enatbanksc.casemanagementsystem.case_management.CaseType;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CaseTypeDto extends Auditable {
    private Long caseTypeId;
    @NotEmpty(message = "Case Type Name can not be empty!")
    private String caseTypeName;
    private String caseTypeColor;
//    private Litigation litigation;
}
