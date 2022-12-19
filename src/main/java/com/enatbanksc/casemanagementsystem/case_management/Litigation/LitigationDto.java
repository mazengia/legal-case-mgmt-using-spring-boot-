package com.enatbanksc.casemanagementsystem.case_management.Litigation;


import com.enatbanksc.casemanagementsystem.case_management.Advocate.Advocate;
import com.enatbanksc.casemanagementsystem.case_management.CaseType.CaseType;
import com.enatbanksc.casemanagementsystem.case_management.Intervene.Intervene;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.PlaintiffDefendant;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

import java.util.List;

@Data
public class LitigationDto extends Auditable {
    private Long litigationId;
    private String fileNumber;
    private String courtAdjudicating;
    private LitigationType litigationType;
    private Boolean isBankPlaintiff  ;
    private CaseStage caseStage;
    private CaseOwnerBranchDto branch;
    private PlaintiffDefendant plaintiff;
    private PlaintiffDefendant defendant;
    private LitigationEmployee attorneyHandlingTheCase;
    private Intervene intervene;
    private Advocate advocate;
    private CaseType caseType;
    private List<JudicialAppointment> judicialAppointment;

}
