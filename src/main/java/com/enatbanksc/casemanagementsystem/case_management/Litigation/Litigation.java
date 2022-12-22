package com.enatbanksc.casemanagementsystem.case_management.Litigation;


import com.enatbanksc.casemanagementsystem.case_management.Advocate.Advocate;
import com.enatbanksc.casemanagementsystem.case_management.CaseType.CaseType;
import com.enatbanksc.casemanagementsystem.case_management.Intervene.Intervene;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.PlaintiffDefendant;
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
    private Long litigationId;
    private String fileNumber;
    private String courtAdjudicating;
    private String status;
    private LitigationType litigationType;
    private Boolean isBankPlaintiff  ;
    private CaseStage caseStage;
    private String attorneyHandlingTheCase;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "case_type_id",nullable = true)
    @JsonIgnoreProperties(value={"litigation"} )
    private CaseType caseType;


    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "advocate_id",nullable = true)
    @JsonIgnoreProperties(value={"litigation"} )
    private Advocate advocate;

    @ManyToOne(fetch = FetchType.EAGER  )
    @JoinColumn(name = "intervene_id",nullable = true)
    @JsonIgnoreProperties(value={"litigation"} )
    private  Intervene intervene;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "branch.id", column = @Column(name = "branch_id")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "branch_name")),
    })
    private CaseOwnerBranchDto branch;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "plaintiff_first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "plaintiff_middle_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "plaintiff_last_name")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "plaintiff_account_number")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "plaintiff_phone_number")),
    })
    private PlaintiffDefendant plaintiff;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "defendant_first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "defendant_middle_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "defendant_last_name")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "defendant_account_number")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "defendant_phone_number")),
    })
    private PlaintiffDefendant defendant;

    @OneToMany(mappedBy = "litigation")
    private List<JudicialAppointment> judicialAppointment;
//    @OneToMany(mappedBy = "litigation")
//    private List<Appeal> appeals;
//    @OneToMany(mappedBy = "litigation")
//    private List<Comment> comments;
}
