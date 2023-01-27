package com.enatbanksc.casemanagementsystem.case_management.MailNotificationType;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "mail_notification_types")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE mail_notification_types SET deleted = 'true' WHERE mail_notification_type_id=? and version=?")
public class MailNotificationType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailNotificationTypeId;
    private String mailTypeName;
    private Integer numberOfDays;
    private String color;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mailNotificationType")
//    private MortgageType mortgageType;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;
}
