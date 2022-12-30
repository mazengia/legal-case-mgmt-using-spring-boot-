package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.Appeal;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class AppealApplicantRespondentDto extends Auditable {
    private Long appealApplicantRespondentId;
    private String applicant;
    private String respondent;
    private Appeal appeal ;
}
