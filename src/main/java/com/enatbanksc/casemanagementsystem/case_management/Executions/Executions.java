package com.enatbanksc.casemanagementsystem.case_management.Executions;


import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "executions")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE executions SET deleted = 1 WHERE id=? and version=?")
public class Executions extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  executionsId;
    private String fileNumber;
    private String courtAdjudicating;
    private String judgmentGetter;
    private String caseStage;
    private String attorneyHandlingTheCase;
    private  String  caseType;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "branch.id", column = @Column(name = "branch_id")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "branch_name")),
    })
    private CaseOwnerBranchDto branch;

//    @OneToMany(mappedBy = "executions")
//    private List<JudicialAppointment> judicialAppointment;
}
