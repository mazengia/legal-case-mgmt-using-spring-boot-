package com.enatbanksc.casemanagementsystem.case_management.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface MortgageDetailRepository extends PagingAndSortingRepository<MortgageDetail, Long>, JpaSpecificationExecutor<MortgageDetail> {
    Page<MortgageDetail> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<MortgageDetail> findMortgageDetailByBranchIdOrderByCreatedAtDesc(Pageable pageable, Long branchId);
    Page<MortgageDetail> findMortgageDetailByStatusOrderByCreatedAtDesc(Pageable pageable, String Status);
    Page<MortgageDetail> findMortgageDetailByAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorney);

}