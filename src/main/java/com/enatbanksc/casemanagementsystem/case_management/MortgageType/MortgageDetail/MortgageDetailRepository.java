package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface MortgageDetailRepository extends PagingAndSortingRepository<MortgageDetail, Long>, JpaSpecificationExecutor<MortgageDetail> {
}
