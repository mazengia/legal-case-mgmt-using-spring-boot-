package com.enatbanksc.casemanagementsystem.case_management.SentEmail;

import com.enatbanksc.casemanagementsystem.case_management.ForeClosure.ForeClosure;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class EmailDto extends Auditable {
    private Long id;
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private boolean sent;
    private MortgageDetail mortgageDetail;
    private Litigation litigation;
}
