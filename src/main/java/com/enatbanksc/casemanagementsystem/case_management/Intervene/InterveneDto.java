package com.enatbanksc.casemanagementsystem.case_management.Intervene;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.Address;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
//@EqualsAndHashCode(callSuper = true)

public class InterveneDto extends Auditable {
    private Long interveneId;
//    @NotEmpty(message = "First Name may not be Empty!")
    private String firstName;
//    @NotEmpty(message = "Last Name may not be Empty!")
    private String lastName;
    private Address interveneAddress;
//    private Litigation litigation;
    private Employee maintained_by;
}
