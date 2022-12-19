package com.enatbanksc.casemanagementsystem.case_management._config.Common;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class PlaintiffDefendant {
    private String firstName;
    private String middleName;
    private String lastName;
    private String accountNumber;
    private String phoneNumber;
}
