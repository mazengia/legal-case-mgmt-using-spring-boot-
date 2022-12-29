package com.enatbanksc.casemanagementsystem.case_management.Executions;

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
public interface ExecutionsRepository extends PagingAndSortingRepository<Executions, Long>, JpaSpecificationExecutor<Executions> {
//    Page<Executions> findLitigationByCaseStageOrderByCreatedAtDesc(Pageable pageable, CaseStage caseStage);
//    Page<Executions> findLitigationByBranchIdOrderByCreatedAtDesc(Pageable pageable, Long branchId);
//    Page<Executions> findLitigationByAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorney);
//    Page<Executions> findLitigationOrderByCreatedAtDesc(Pageable pageable, String Status);
//
//    @Query(value = "select * from executions sl where (sl.litigation_type like %:value% or sl.court_adjudicating like %:value% or sl.status like %:value%    or sl.attorney_handling_the_case like %:value% )" , nativeQuery = true)
//    Page<Executions> findByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value);
//    @Query(value = "select * from executions s where (s.litigation_type like %:value% or s.court_adjudicating like %:value% or s.status like %:value%   or s.attorney_handling_the_case like %:value%) and  s.id  =:branchId" , nativeQuery = true)
//    Page<Executions> findByLitigationTypeOrCourtAdjudicatingOrOrAttorneyHandlingTheCaseAndBranchIdOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("branchId") Long branchId);
//
//    @Query(value = "select * from executions s where (s.litigation_type like %:value% or s.court_adjudicating like %:value% ) and  s.attorney_handling_the_case  =:attorneyHandlingTheCase" , nativeQuery = true)
//    Page<Executions> findAllByLitigationTypeOrCourtAdjudicatingAndAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("attorneyHandlingTheCase") String attorneyHandlingTheCase);
//
//    @Query(value = "select * from executions s where (s.litigation_type like %:value% or s.court_adjudicating like %:value%   or s.attorney_handling_the_case like %:value% ) and  s.status  =:status" , nativeQuery = true)
//    Page<Executions> findAllByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("status") String status);

    Page<Executions> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
