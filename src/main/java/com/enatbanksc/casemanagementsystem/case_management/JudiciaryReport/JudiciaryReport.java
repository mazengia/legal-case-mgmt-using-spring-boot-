package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import com.enatbanksc.casemanagementsystem.case_management.Comment.Comment;
import com.enatbanksc.casemanagementsystem.case_management.Common.Address;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment.Adjournment;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.settings.Expense.Expense;
import com.enatbanksc.casemanagementsystem.case_management.settings.Expense.ExpenseDetails.ExpenseDetail;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "judiciary_reports")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE judiciary_reports SET deleted = 1 WHERE id=? and version=?")
public class JudiciaryReport extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    private String courtAddress;
    private Double judicialFee;
    private LocalDateTime judicialAppointmentDate;
    private String adjournmentReason;
    @OneToMany(mappedBy = "judiciaryReport")
    private List<Adjournment> nextAppointment = new ArrayList<>();

    @OneToMany(mappedBy = "judiciaryReport")
    private List<ExpenseDetail> expenseDetails = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

    @ManyToOne
    private Litigation litigation;

}
