package com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionDetails;

import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuctionDetailDto extends Auditable {
    private Long auctionDetailId;
    private LocalDateTime dateAuctionAnnounced;
    private LocalDateTime dateAuctionWillBeConducted;
//    private AuctionType auctionType;
}
