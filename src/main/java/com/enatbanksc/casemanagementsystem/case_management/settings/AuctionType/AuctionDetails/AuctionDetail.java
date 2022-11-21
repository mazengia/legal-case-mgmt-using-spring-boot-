package com.enatbanksc.casemanagementsystem.case_management.settings.AuctionType.AuctionDetails;

import com.enatbanksc.casemanagementsystem.case_management.settings.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management.settings.CaseType.CaseType;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="AuctionDetail")
@Table(name="auction_details")
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE auction_details SET deleted = 1 WHERE id=? and version=?")
public class AuctionDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auctionDetailId;
    private LocalDateTime dateAuctionAnnounced;
    private LocalDateTime dateAuctionWillBeConducted;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "auction_type_id")
    private AuctionType auctionType;

}
