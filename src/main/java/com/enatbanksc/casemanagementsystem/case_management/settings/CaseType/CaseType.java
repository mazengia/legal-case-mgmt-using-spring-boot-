package com.enatbanksc.casemanagementsystem.case_management.settings.CaseType;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "case_types")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE case_types SET deleted = 1 WHERE id=? and version=?")
public class CaseType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseTypeId;
    @NotEmpty(message = "Case Type Name can not be empty!")
    private String caseTypeName;
    private String caseTypeColor;

    @OneToOne(fetch = FetchType.LAZY)
    private Litigation litigation;
}
