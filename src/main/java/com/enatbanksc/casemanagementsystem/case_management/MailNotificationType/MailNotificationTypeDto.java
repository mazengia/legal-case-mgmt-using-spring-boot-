package com.enatbanksc.casemanagementsystem.case_management.MailNotificationType;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class MailNotificationTypeDto extends Auditable {
    private Long id;
    private String mailTypeName;
    private Integer numberOfDays;
    private String color;
    private Employee maintainer;
}
