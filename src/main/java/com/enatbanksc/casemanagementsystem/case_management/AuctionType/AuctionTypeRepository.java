package com.enatbanksc.casemanagementsystem.case_management.AuctionType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AuctionTypeRepository extends PagingAndSortingRepository<AuctionType, Long>, JpaSpecificationExecutor<AuctionType> {
    Page<AuctionType> findAllByDeletedIsFalseOrderByAuctionTypeIdDesc(Pageable pageable);
    Page<AuctionType> findAllByMortgageDetailMortgageDetailIdAndDeletedIsFalseOrderByAuctionTypeIdDesc(Pageable pageable, long id);
}
