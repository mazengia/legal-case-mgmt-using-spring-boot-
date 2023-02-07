package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name = "foreclosure")
@Table(name = "foreclosure")
@Data

@SQLDelete(sql = "UPDATE foreclosure SET deleted = 'true'  WHERE foreclosure_id=? and version=?")
@Where(clause = "deleted=false")
public class Foreclosure extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foreclosureId;
    private Boolean enabled= Boolean.FALSE;;
    private Boolean isWrittenLegalNoticeServed;
    private String expireDateLegalNoticeServed;
    private String dateLegalNoticeServed;
    private Boolean isCollateralEstimated;
    private String dateCollateralIsEstimated;
    private String attorneyHandlingTheCase;
    private String mortgagor;
    private String borrower;
    @Column(columnDefinition = "varchar(255) default 'Pending'")
    private String status;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "branch.id", column = @Column(name = "branch_id")),
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintainer;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "branch.id", column = @Column(name = "branch_id")),
//            @AttributeOverride(name = "branch.code", column = @Column(name = "branch_code")),
//            @AttributeOverride(name = "branch.name", column = @Column(name = "branch_name")),
//    })
//    private CaseOwnerBranchDto branch;
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "plateNumber", column = @Column(name = "plateNumber")),
//            @AttributeOverride(name = "shansiNumber", column = @Column(name = "shansiNumber")),
//            @AttributeOverride(name = "motorNumber", column = @Column(name = "motorNumber"))
//    })
//    private Vehicle vehicle;


//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "mortgage_type_id")
//    @JsonIgnoreProperties(value = {"mortgageDetail"})
//    private MortgageType mortgageType;
//    @OneToMany(mappedBy = "foreclosure")
//    private List<AuctionType> AuctionType;
}
