package com.enatbanksc.casemanagementsystem.case_management.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MortgageDetail")
@Table(name = "mortgage_details")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE mortgage_details SET deleted = 1 WHERE id=? and version=?")
public class MortgageDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mortgageDetailId;
    private String machineryType;
    private String machineryTypeTwo;
    private String machineryTypeThree;
    private String machineryTypeFour;
//    private String machineryTypeFive;
    private String numberOfTitleIndeed;
    private String plateNumber;
    private String shansiNumber;
    private String motorNumber;
    private Boolean isWrittenLegalNoticeServed;
    private String dateLegalNoticeServed;
    private Boolean isCollateralEstimated;
    private String dateCollateralIsEstimated;
    private String attorneyHandlingTheCase;
    private String mortgagor;
    private String borrower;
    @Column(columnDefinition = "varchar(255) default 'Pending'")
    private String status;
    private String mortgageTypeName;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "branch.id", column = @Column(name = "branch_id")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "branch_name")),
    })
    private CaseOwnerBranchDto branch;
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
    @OneToMany(mappedBy = "mortgageDetail")
    private List<AuctionType> AuctionType;
}
