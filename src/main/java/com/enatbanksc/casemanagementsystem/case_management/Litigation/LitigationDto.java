package com.enatbanksc.casemanagementsystem.case_management.Litigation;


import com.enatbanksc.casemanagementsystem.case_management.Advocate.Advocate;
import com.enatbanksc.casemanagementsystem.case_management.CaseType.CaseType;
import com.enatbanksc.casemanagementsystem.case_management.Intervene.Intervene;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.PlaintiffDefendant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LitigationDto extends   RepresentationModel<LitigationDto> {
    private Long litigationId;
    private String fileNumber;
    private String courtAdjudicating;
    private String litigationType;
    private Boolean isBankPlaintiff  ;
    private CaseStage caseStage;
    private String plaintiff;
    private String defendant;
    private CaseOwnerBranchDto branch;
//    private PlaintiffDefendant plaintiff;
//    private PlaintiffDefendant defendant;
    private String attorneyHandlingTheCase;
    private String status;
    private Intervene intervene;
//    private Advocate advocate;
    private CaseType caseType;
    private List<JudicialAppointment> judicialAppointment;
//    private List<Comment> comments;

}
