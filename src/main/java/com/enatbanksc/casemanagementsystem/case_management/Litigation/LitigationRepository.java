package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
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
public interface LitigationRepository extends PagingAndSortingRepository<Litigation, Long>, JpaSpecificationExecutor<Litigation> {
    Page<Litigation> findLitigationByCaseStageOrderByCreatedAtDesc(Pageable pageable, CaseStage caseStage);
    Page<Litigation> findLitigationByBranchIdOrderByCreatedAtDesc(Pageable pageable, Long branchId);
    Page<Litigation> findLitigationByAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<Litigation> findLitigationByStatusOrderByCreatedAtDesc(Pageable pageable, String Status);

    @Query(value = "select * from litigations sl where (sl.litigation_type like %:value% or sl.court_adjudicating like %:value% or sl.status like %:value%    or sl.attorney_handling_the_case like %:value% )" , nativeQuery = true)
    Page<Litigation> findByLitigationTypeOrCourtAdjudicatingOrStatusOrAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value);
    @Query(value = "select * from litigations s where (s.litigation_type like %:value% or s.court_adjudicating like %:value% or s.status like %:value%   or s.attorney_handling_the_case like %:value%) and  s.id  =:branchId" , nativeQuery = true)
    Page<Litigation> findByLitigationTypeOrCourtAdjudicatingOrStatusOrAttorneyHandlingTheCaseAndBranchIdOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("branchId") Long branchId);

    @Query(value = "select * from litigations s where (s.litigation_type like %:value% or s.court_adjudicating like %:value% ) and  s.attorney_handling_the_case  =:attorneyHandlingTheCase" , nativeQuery = true)
    Page<Litigation> findAllByLitigationTypeOrCourtAdjudicatingOrStatusAndAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("attorneyHandlingTheCase") String attorneyHandlingTheCase);

    @Query(value = "select * from litigations s where (s.litigation_type like %:value% or s.court_adjudicating like %:value%   or s.attorney_handling_the_case like %:value% ) and  s.status  =:status" , nativeQuery = true)
    Page<Litigation> findAllByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseAndStatusOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("status") String status);

    Page<Litigation> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
