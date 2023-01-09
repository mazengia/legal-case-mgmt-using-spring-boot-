package com.enatbanksc.casemanagementsystem.case_management.Litigation;


import com.enatbanksc.casemanagementsystem.case_management.Intervene.Intervene;
import com.enatbanksc.casemanagementsystem.case_management.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "litigations")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE litigations SET deleted = 1 WHERE id=? and version=?")
public class Litigation extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   litigationId;
    private String fileNumber;
    private String courtAdjudicating;
    @Column(columnDefinition = "varchar(255) default 'Pending'")
    private String status;
    private String disputedAmount;
    private Boolean isBankPlaintiff;
    private String caseStage;
    private String attorneyHandlingTheCase;
    private String caseType;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "intervene_id", nullable = true)
    @JsonIgnoreProperties(value = {"litigation"})
    private Intervene intervene;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "branch.id", column = @Column(name = "branch_id")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "branch_name")),
    })
    private CaseOwnerBranchDto branch;

}
