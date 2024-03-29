package com.enatbanksc.casemanagementsystem.case_management.AuctionType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuctionTypeMapper {
    AuctionType toAuctionType(AuctionTypeDto auctionTypeDto);
    AuctionTypeDto toAuctionTypeDto(AuctionType auctionType);
}
