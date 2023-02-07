package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ForeclosureRepository extends PagingAndSortingRepository<Foreclosure, Long>, JpaSpecificationExecutor<Foreclosure> {
    Page<Foreclosure> findAllByDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(Pageable pageable);

    Page<Foreclosure> findForeclosureByMaintainerBranchIdAndDeletedIsFalseOrderByForeclosureIdDesc(Pageable pageable, Long branchId);

    @Query(value = "select * from foreclosure l where  l.id  !=:branchId", nativeQuery = true)
    Page<Foreclosure> findAllByBranchIdAndDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(Pageable pageable, @Param("branchId") Long branchId);

    Page<Foreclosure> findForeclosureByStatusAndDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(Pageable pageable, String Status);

    Page<Foreclosure> findForeclosureByAttorneyHandlingTheCaseAndDeletedIsFalseAndEnabledIsTrueOrderByForeclosureIdDesc(Pageable pageable, String attorney);
    @Query(value = "select * from foreclosure where deleted='false' and enabled='true'", nativeQuery = true)
    Iterable<Foreclosure> report();
//    Iterable<Foreclosure> findAllByDeletedIsFalseAndEnabledIsTrue ();
}
