package com.enatbanksc.casemanagementsystem.case_management.Litigation;


import com.enatbanksc.casemanagementsystem.case_management.Intervene.Intervene;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LitigationDto extends Auditable {
    private Long litigationId;
    private String fileNumber;
    private String courtAdjudicating;
    private String litigationType;
    private Boolean isBankPlaintiff  ;
    private String caseStage;
    private CaseOwnerBranchDto branch;
    private String attorneyHandlingTheCase;
    private String status;
    private Intervene intervene;
    private String content;
    private  String  caseType;
    private List<JudicialAppointment> judicialAppointment;

}
