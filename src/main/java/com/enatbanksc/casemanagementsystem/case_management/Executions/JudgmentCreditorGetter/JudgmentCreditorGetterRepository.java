package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface JudgmentCreditorGetterRepository extends PagingAndSortingRepository<JudgmentCreditorGetter, Long>, JpaSpecificationExecutor<JudgmentCreditorGetter> {
    Page<JudgmentCreditorGetter> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<JudgmentCreditorGetter> findAllByExecutionsExecutionsIdOrderByCreatedAtDesc(long executionsId, Pageable pageable);
}
