package com.enatbanksc.casemanagementsystem.case_management.settings.AuctionType.AuctionDetails;

import com.enatbanksc.casemanagementsystem.case_management.settings.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
public class AuctionDetailDto extends Auditable {
    private Long auctionDetailId;
    private LocalDateTime dateAuctionAnnounced;
    private LocalDateTime dateAuctionWillBeConducted;
    private AuctionType auctionType;
}
