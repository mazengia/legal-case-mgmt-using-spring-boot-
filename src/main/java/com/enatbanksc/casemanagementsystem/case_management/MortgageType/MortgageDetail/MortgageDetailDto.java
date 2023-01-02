package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class MortgageDetailDto   extends Auditable {
    private Long mortgageDetailId;
    private String machineryType;
    private String numberOfTitleIndeed;
    private String plateNumber;
    private String shansiNumber;
    private String motorNumber;
    private String dateCollateralRegistered;
    private Boolean isCollateralEstimated;
    private String dateCollateralIsEstimated;
    private Boolean isWrittenLegalNoticeServed;
    private String dateLegalNoticeServed;
    private String mortgagor;
    private String borrower;
    private String status;
    private String mortgageTypeName;
    private Employee maintained_by;
//    private Vehicle vehicle;
//    private MortgageType mortgageType;
    private List<AuctionType> AuctionType;

}