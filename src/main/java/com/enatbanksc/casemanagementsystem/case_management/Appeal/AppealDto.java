package com.enatbanksc.casemanagementsystem.case_management.Appeal;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant.AppealApplicantRespondent;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class AppealDto extends Auditable {
    private Long appealId;
    private String reason;
    private String fileNumber;
    private String courtAdjudicating;
    private Litigation litigation;
    private String disputedAmount;
    private Employee maintained_by;
//    @JsonManagedReference
//    private AppealApplicantRespondent appealApplicantRespondents;
    private List<AppealApplicantRespondent> appealApplicantRespondents;
}
