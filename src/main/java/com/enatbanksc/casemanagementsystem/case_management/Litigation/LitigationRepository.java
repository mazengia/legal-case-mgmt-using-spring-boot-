package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface LitigationRepository extends PagingAndSortingRepository<Litigation, Long>, JpaSpecificationExecutor<Litigation> {
    Page<Litigation> findLitigationByCaseStageAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, CaseStage caseStage);
    Page<Litigation> findLitigationByBranchIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long branchId);
    Page<Litigation> findAllByBranchIdIsNotContainingOrderByCreatedAtDesc(Pageable pageable, long branchId);
    @Query(value = "select * from litigations s where  s.id  !=:branchId" , nativeQuery = true)
    Page<Litigation> findAllByBranchIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, @Param("branchId") Long branchId);
    Page<Litigation> findLitigationByAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<Litigation> findLitigationByStatusAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String Status);

    @Query(value = "select * from litigations sl where (sl.case_type like %:value% or sl.court_adjudicating like %:value% or sl.status like %:value%    or sl.attorney_handling_the_case like %:value% )" , nativeQuery = true)
    Page<Litigation> findByCaseTypeOrCourtAdjudicatingOrStatusOrAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value);
    @Query(value = "select * from litigations s where (s.case_type like %:value% or s.court_adjudicating like %:value% or s.status like %:value%   or s.attorney_handling_the_case like %:value%) and  s.id  =:branchId" , nativeQuery = true)
    Page<Litigation> findByCaseTypeOrCourtAdjudicatingOrStatusOrAttorneyHandlingTheCaseAndBranchIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("branchId") Long branchId);

    @Query(value = "select * from litigations s where (s.case_type like %:value% or s.court_adjudicating like %:value% ) and  s.attorney_handling_the_case  =:attorneyHandlingTheCase" , nativeQuery = true)
    Page<Litigation> findAllByCaseTypeOrCourtAdjudicatingOrStatusAndAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("attorneyHandlingTheCase") String attorneyHandlingTheCase);

    @Query(value = "select * from litigations s where (s.case_type like %:value% or s.court_adjudicating like %:value%   or s.attorney_handling_the_case like %:value% ) and  s.status  =:status" , nativeQuery = true)
    Page<Litigation> findAllByCaseTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseAndStatusAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, @Param("value") String value, @Param("status") String status);
    Page<Litigation> findAllByDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, JwtAuthenticationToken token);


}
