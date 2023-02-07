package com.enatbanksc.casemanagementsystem.case_management.AuctionType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface AuctionTypeRepository extends PagingAndSortingRepository<AuctionType, Long>, JpaSpecificationExecutor<AuctionType> {
    Page<AuctionType> findAllByDeletedIsFalseOrderByIdDesc(Pageable pageable);
    List<AuctionType> findAllByDeletedIsFalse();
    Page<AuctionType> findAllByForeclosureForeclosureIdAndDeletedIsFalseOrderByIdDesc(Pageable pageable, long id);



}
