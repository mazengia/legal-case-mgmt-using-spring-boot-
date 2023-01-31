package com.enatbanksc.casemanagementsystem.case_management.Appeal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AppealRepository extends PagingAndSortingRepository<Appeal, Long>, JpaSpecificationExecutor<Appeal> {
    Page<Appeal> findAllByAndDeletedIsFalseOrderByAppealIdDesc(Pageable pageable);
    Page<Appeal> findAllByLitigationAttorneyHandlingTheCaseAndDeletedIsFalseOrderByAppealIdDesc(Pageable pageable, String attorneyHandlingTheCase);
    Page<Appeal> findAllByLitigationLitigationIdAndDeletedIsFalseOrderByAppealIdDesc(Pageable pageable, long id);

}
