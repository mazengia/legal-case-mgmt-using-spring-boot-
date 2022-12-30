package com.enatbanksc.casemanagementsystem.case_management.Appeal;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class AppealDto extends Auditable {
    private Long appealId;
    private String reason;
    private String fileNumber;
    private String courtAdjudicating;
    private Litigation litigation;
//    private List<AppealApplicantRespondent> appealApplicantRespondents;
    private Employee maintained_by;
}
