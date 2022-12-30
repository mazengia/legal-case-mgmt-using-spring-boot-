package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class DefendantPlaintiffDto extends Auditable {
    private Long defendantPlaintiffId;
    private String plaintiff;
    private String defendant;
    private Litigation litigation ;
}
