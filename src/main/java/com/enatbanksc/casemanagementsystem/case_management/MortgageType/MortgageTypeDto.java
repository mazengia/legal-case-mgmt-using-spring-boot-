package com.enatbanksc.casemanagementsystem.case_management.MortgageType;

import com.enatbanksc.casemanagementsystem.case_management.MailNotificationType.MailNotificationType;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class MortgageTypeDto extends Auditable {
    private Long mortgageTypeId;
    private String mortgageTypeName;
    private String remark;
    private MailNotificationType mailNotificationType;
    private Employee maintained_by;
}
