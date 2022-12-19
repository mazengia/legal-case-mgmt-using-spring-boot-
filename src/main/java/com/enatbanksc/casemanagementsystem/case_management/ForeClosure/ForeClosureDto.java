package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForeClosureDto extends Auditable {
    private Long foreClosureId;
    private String dateAuctionAnnounced;
    private String dateAuctionConducted;;
    private String status;
    private MortgageDetail mortgageDetail;
    private AuctionType auctionType;
}