package com.enatbanksc.casemanagementsystem.case_management.Files;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface FilesRepository extends PagingAndSortingRepository<Files, Long>, JpaSpecificationExecutor<Files> {
    Page<Files> findAllByOrderByCreatedAtDesc(Pageable pageable);

    boolean existsAttachedFilesByFileName(String fileName);

    Page<Files> findAllByLitigationLitigationIdAndLitigationLitigationIdNotNullOrderByCreatedAtDesc(Pageable pageable, long id);

    Page<Files> findAllByMortgageDetailMortgageDetailIdOrderByCreatedAtDesc(Pageable pageable, long id);

    Page<Files> findAllByAppealAppealIdAndAppealAppealIdNotNullOrderByCreatedAtDesc(Pageable pageable, long id);

    Page<Files> findAllByExecutionsExecutionsIdAndExecutionsExecutionsIdNotNullOrderByCreatedAtDesc(Pageable pageable, long id);
    void deleteByFileId(long id);
          Files getByFileId(long id) ;

    void deleteByFileName(String fileName);
}
