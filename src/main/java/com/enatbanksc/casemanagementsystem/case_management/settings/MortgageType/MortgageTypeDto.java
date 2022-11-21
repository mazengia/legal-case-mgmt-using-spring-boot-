package com.enatbanksc.casemanagementsystem.case_management.settings.MortgageType;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.settings.MailNotificationType.MailNotificationType;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;

@Data
public class MortgageTypeDto extends Auditable {
    private Long mortgageTypeId;
    private String mortgageTypeName;
    private String remark;
    private MailNotificationType mailNotificationType;
    private Employee maintained_by;
}
