package com.enatbanksc.casemanagementsystem.case_management.Executions;


import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
//@AllArgsConstructor
public class ExecutionsDto extends Auditable {
    private Long executionsId;
    private String fileNumber;
    private String courtAdjudicating;
    private String caseStage;
    private String disputedAmount;
    private String attorneyHandlingTheCase;
    private  String  caseType;

}
