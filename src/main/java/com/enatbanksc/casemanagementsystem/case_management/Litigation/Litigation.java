package com.enatbanksc.casemanagementsystem.case_management.Litigation;


import com.enatbanksc.casemanagementsystem.case_management.Comment.Comment;
import com.enatbanksc.casemanagementsystem.case_management.Common.CaseStage;
import com.enatbanksc.casemanagementsystem.case_management.Common.PlaintiffDefendant;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment.Adjournment;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Advocate.Advocate;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene.Intervene;
import com.enatbanksc.casemanagementsystem.case_management.settings.CaseType.CaseType;
import com.enatbanksc.casemanagementsystem.case_management.settings.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
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
    private LitigationType litigationType;
    private Boolean isBankPlaintiff = Boolean.TRUE;
    private CaseStage caseStage;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "case_type_id")
    private CaseType caseType;

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "attorney_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "attorney_employee_fullName")),
            @AttributeOverride(name = "contact.email", column = @Column(name = "attorney_email")),
    })
    private LitigationEmployee attorneyHandlingTheCase;

}
