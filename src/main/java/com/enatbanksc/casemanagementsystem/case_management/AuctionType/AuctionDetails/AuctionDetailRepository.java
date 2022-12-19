package com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionDetails;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AuctionDetailRepository extends PagingAndSortingRepository<AuctionDetail, Long>, JpaSpecificationExecutor<AuctionDetail> {
}
