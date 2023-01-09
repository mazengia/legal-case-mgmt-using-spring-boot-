package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.Appeal;
import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
//@Where(clause = "deleted=0")
//@SQLDelete(sql = "UPDATE files SET deleted = 1 WHERE fileId=? and version=?")
public class Files extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    private String fileName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "litigationId",nullable = true)
    @JsonIgnoreProperties(value = {"files"})
    private Litigation litigation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mortgageDetailId",nullable = true)
    @JsonIgnoreProperties(value = {"files"})
    private MortgageDetail   mortgageDetail;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "appealId",nullable = true)
    @JsonIgnoreProperties(value = {"files"})
    private Appeal appeal;
    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "executionsId",nullable = true)
    @JsonIgnoreProperties(value = {"files"})
    private Executions executions;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

}
