package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface DefendantPlaintiffRepository extends PagingAndSortingRepository<DefendantPlaintiff, Long>, JpaSpecificationExecutor<DefendantPlaintiff> {
    Page<DefendantPlaintiff> findAllByDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    Page<DefendantPlaintiff> findAllByLitigationLitigationIdAndDeletedIsFalseOrderByCreatedAtDesc(Long litigationId, Pageable pageable);
}
