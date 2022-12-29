package com.enatbanksc.casemanagementsystem.case_management.Appeal;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "appeal")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE appeal SET deleted = 1 WHERE id=? and version=?")
public class Appeal extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appealId;
    private String fileNumber;
    private String courtAdjudicating;
    private String applicant;
    private String respondent;

    private String reason;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "litigationId")
    @JsonIgnoreProperties(value = {"appeal"})
    private Litigation litigation;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

}
