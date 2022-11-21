package com.enatbanksc.casemanagementsystem.case_management.settings.MailNotificationType;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;

@Data
public class MailNotificationTypeDto extends Auditable {
    private Long mailNotificationTypeId;
    private String mailTypeName;
    private Integer numberOfDays;
    private String color;
    private Employee maintained_by;
}
