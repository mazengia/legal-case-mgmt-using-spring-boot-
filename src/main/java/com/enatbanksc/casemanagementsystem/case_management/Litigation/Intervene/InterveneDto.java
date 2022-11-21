package com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene;

import com.enatbanksc.casemanagementsystem.case_management.Common.Address;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class InterveneDto extends Auditable {
    private Long interveneId;
    @NotEmpty(message = "First Name may not be Empty!")
    private String firstName;
    @NotEmpty(message = "Last Name may not be Empty!")
    private String lastName;
    private Address interveneAddress;
    private Employee maintained_by;
    private Litigation litigation;
}
