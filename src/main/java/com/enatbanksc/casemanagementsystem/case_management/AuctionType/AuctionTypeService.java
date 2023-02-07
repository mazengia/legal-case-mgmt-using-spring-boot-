package com.enatbanksc.casemanagementsystem.case_management.AuctionType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuctionTypeService {
    AuctionType createAuctionType(AuctionType auctionType, JwtAuthenticationToken token) throws IllegalAccessException;
    AuctionType getAuctionById(long auctionTypeId);
    Page<AuctionType> getAuctionTypes(Pageable pageable, JwtAuthenticationToken token);

    List<AuctionType> getAllAuctionTypes();

    Page<AuctionType> getAuctionTypesByMortgageDetail(Pageable pageable, long id, JwtAuthenticationToken token);

    AuctionType updateAuctionType(long auctionTypeId, AuctionType auctionType, JwtAuthenticationToken token) throws IllegalAccessException;
    void deleteExpenseById(long auctionTypeId);
}
