package com.enatbanksc.casemanagementsystem.case_management.Files;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AttachedFilesRepository extends PagingAndSortingRepository<AttachedFiles, Long>, JpaSpecificationExecutor<AttachedFiles> {
    Page<AttachedFiles> findAllByOrderByCreatedAtDesc(Pageable pageable);
    boolean existsAttachedFilesByFileName(String fileName);

    Page<AttachedFiles> findAllByLitigationLitigationIdOrderByCreatedAtDesc(Pageable pageable, long id);
    Page<AttachedFiles> findAllByMortgageDetailMortgageDetailIdOrderByCreatedAtDesc(Pageable pageable,   long id);
    Page<AttachedFiles> findAllByAppealAppealIdOrderByCreatedAtDesc(Pageable pageable,   long id);



}
