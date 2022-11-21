package com.enatbanksc.casemanagementsystem.case_management.Litigation;


import com.enatbanksc.casemanagementsystem.case_management.Comment.Comment;
import com.enatbanksc.casemanagementsystem.case_management.Common.CaseStage;
import com.enatbanksc.casemanagementsystem.case_management.Common.PlaintiffDefendant;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment.Adjournment;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management.settings.CaseType.CaseType;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class LitigationDto extends Auditable {
    private Long litigationId;
    private String fileNumber;
    private String courtAdjudicating;
    private LitigationType litigationType;
    private Boolean isBankPlaintiff = Boolean.TRUE;
    private CaseStage caseStage;
    private CaseType caseType;
    private CaseOwnerBranchDto branch;
    private PlaintiffDefendant plaintiff;
    private PlaintiffDefendant defendant;
    private LitigationEmployee attorneyHandlingTheCase;

}
