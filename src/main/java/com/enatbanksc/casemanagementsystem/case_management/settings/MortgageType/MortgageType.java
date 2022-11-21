package com.enatbanksc.casemanagementsystem.case_management.settings.MortgageType;


import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management.settings.MailNotificationType.MailNotificationType;
import com.enatbanksc.casemanagementsystem.case_management.settings.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MortgageType")
@Table(name = "mortgage_types")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE mortgage_types SET deleted = 1 WHERE id=? and version=?")
public class MortgageType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mortgageTypeId;
    private String mortgageTypeName;
    private String remark;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mailNotificationType_id", referencedColumnName = "mailNotificationTypeId")
    private MailNotificationType mailNotificationType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;


}
