package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageType;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Vehicle;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MortgageDetailDto   extends Auditable {
    private Long mortgageDetailId;
    private String machineryType;
    private String numberOfTitleIndeed;
    private String dateCollateralRegistered;
    private Boolean isCollateralEstimated;
    private String dateCollateralIsEstimated;
    private Boolean isWrittenLegalNoticeServed;
    private String dateLegalNoticeServed;
    private String mortgagor;
    private String borrower;
    private Vehicle vehicle;
    private MortgageType mortgageType;

}