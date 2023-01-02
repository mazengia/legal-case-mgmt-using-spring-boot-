package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class JudgmentCreditorGetterDto extends Auditable {
    private Long judgmentCreditorGetterId;
    private String judgmentCreditor;
    private String judgmentGetter;
    private Executions executions ;
}
