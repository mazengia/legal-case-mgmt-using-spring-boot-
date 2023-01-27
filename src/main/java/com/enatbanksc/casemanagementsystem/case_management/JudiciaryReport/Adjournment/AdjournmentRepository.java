package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AdjournmentRepository extends PagingAndSortingRepository<Adjournment, Long>, JpaSpecificationExecutor<Adjournment> {
    Page<Adjournment> findAllByDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
}
