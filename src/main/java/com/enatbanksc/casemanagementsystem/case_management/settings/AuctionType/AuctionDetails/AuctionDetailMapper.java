package com.enatbanksc.casemanagementsystem.case_management.settings.AuctionType.AuctionDetails;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuctionDetailMapper {
    AuctionDetail toAuctionDetail(AuctionDetailDto auctionDetailDto);
    AuctionDetailDto toAuctionDetailDto(AuctionDetail auctionDetail);
}
