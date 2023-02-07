package com.enatbanksc.casemanagementsystem.case_management.Executions;


import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
@Entity
@Table(name = "executions")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE executions SET deleted = 'true' WHERE executions_id=? and version=?")
public class Executions extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  executionsId;
    private String fileNumber;
    private String courtAdjudicating;
    private String judgmentGetter;
    private String caseStage;
    private String attorneyHandlingTheCase;
    private String disputedAmount;
    private  String  caseType;
}
