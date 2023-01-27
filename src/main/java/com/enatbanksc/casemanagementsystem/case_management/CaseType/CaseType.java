package com.enatbanksc.casemanagementsystem.case_management.CaseType;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "case_types")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE case_types SET deleted = 'true' WHERE case_type_id=? and version=?")
public class CaseType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseTypeId;
    @NotEmpty(message = "Case Type Name can not be empty!")
    private String caseTypeName;
    private String caseTypeColor;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "caseType")
//    private Litigation litigation;
}
