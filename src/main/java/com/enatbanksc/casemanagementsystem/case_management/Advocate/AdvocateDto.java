package com.enatbanksc.casemanagementsystem.case_management.Advocate;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdvocateDto extends Auditable {
    private Long advocateId;
    private String firstName;
    private String lastName;
    private LocalDateTime date;
}
