package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageType;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String numberOfTitleIndeed;
    private String plateNumber;
    private String shansiNumber;
    private String motorNumber;
    private String dateCollateralRegistered;
    private Boolean isWrittenLegalNoticeServed;
    private String dateLegalNoticeServed;
    private Boolean isCollateralEstimated;
    private String dateCollateralIsEstimated;
    private String mortgagor;
    private String borrower;
    private String status;
    private String mortgageTypeName;



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
