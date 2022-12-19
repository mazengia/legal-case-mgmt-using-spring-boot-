package com.enatbanksc.casemanagementsystem.case_management.AuctionType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public interface AuctionTypeService {
    AuctionType createAuctionType(AuctionType auctionType, JwtAuthenticationToken token) throws IllegalAccessException;
    AuctionType getAuctionType(long auctionTypeId);
    Page<AuctionType> getAuctionTypes(Pageable pageable, JwtAuthenticationToken token);
    AuctionType updateAuctionType(long auctionTypeId, AuctionType auctionType, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteExpense(long auctionTypeId, JwtAuthenticationToken token);
}
