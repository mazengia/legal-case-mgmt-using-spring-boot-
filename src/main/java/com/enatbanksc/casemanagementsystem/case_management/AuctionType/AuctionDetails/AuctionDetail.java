package com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionDetails;

import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

//    @ManyToOne(fetch = FetchType.EAGER, optional = false )
//    @JoinColumn(name = "auction_type_id",nullable = false, unique = true)
//    @JsonIgnoreProperties(value={"auctionDetail"} )
//    private AuctionType auctionType;

}
