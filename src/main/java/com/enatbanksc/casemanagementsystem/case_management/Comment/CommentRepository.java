package com.enatbanksc.casemanagementsystem.case_management.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    Page<Comment> findByLitigation_litigationId(long litigationId, Pageable pageable);
    Page<Comment> findByJudiciaryReport_reportId(long reportId, Pageable pageable);
}
