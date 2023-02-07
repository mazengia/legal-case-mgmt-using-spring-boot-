package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;

import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForeclosureDto extends Auditable {
    private Long foreclosureId;
    private String attorneyHandlingTheCase;
    private Boolean enabled;
    private Boolean isCollateralEstimated;
    private String expireDateLegalNoticeServed;
    private String dateCollateralIsEstimated;
    private Boolean isWrittenLegalNoticeServed;
    private String dateLegalNoticeServed;
    private String mortgagor;
    private String borrower;
    private String status;
    private Employee maintainer;

}