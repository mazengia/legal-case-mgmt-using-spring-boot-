package com.enatbanksc.casemanagementsystem.case_management.AuctionType;

import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuctionTypeDto extends Auditable {
    private Long id;
    @NotEmpty(message = "Auction Type Name can not be empty!")
    private String auctionTypeName;
    private String dateAuctionAnnounced;
    private String dateAuctionConducted;
    private String auctionTypeColor;
    private Foreclosure foreclosure;
    private Employee maintainer;
}
