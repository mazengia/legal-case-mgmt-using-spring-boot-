package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AppealApplicantRespondentRepository extends PagingAndSortingRepository<AppealApplicantRespondent, Long>, JpaSpecificationExecutor<AppealApplicantRespondent> {
    Page<AppealApplicantRespondent> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Page<AppealApplicantRespondent> findAllByAppealAppealIdOrderByCreatedAtDesc(long id, Pageable pageable);
}
