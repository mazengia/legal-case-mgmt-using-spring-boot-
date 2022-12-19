package com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionDetails;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuctionDetailMapper {
    AuctionDetail toAuctionDetail(AuctionDetailDto auctionDetailDto);
    AuctionDetailDto toAuctionDetailDto(AuctionDetail auctionDetail);
}
