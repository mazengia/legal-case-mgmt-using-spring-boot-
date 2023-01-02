package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.Appeal;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "attachedFiles")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE attachedFiles SET deleted = 1 WHERE id=? and version=?")
public class AttachedFiles extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    private String fileName;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "litigationId")
    @JsonIgnoreProperties(value = {"attachedFiles"})
    private Litigation litigation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "mortgageDetailId")
    @JsonIgnoreProperties(value = {"attachedFiles"})
    private MortgageDetail mortgageDetail;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "appealId")
    @JsonIgnoreProperties(value = {"attachedFiles"})
    private Appeal appeal;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

}
