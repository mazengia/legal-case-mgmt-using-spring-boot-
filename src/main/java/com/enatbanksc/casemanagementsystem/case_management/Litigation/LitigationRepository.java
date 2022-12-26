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
    Page<Litigation> findLitigationByCaseStage(Pageable pageable, CaseStage caseStage);

    Page<Litigation> findLitigationByBranchId(Pageable pageable, Long branchId);
    Page<Litigation> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney);
    Page<Litigation> findLitigationByStatus(Pageable pageable, String Status);

    @Query(value = "select * from litigation s where s.litigationType like %:value% or s.courtAdjudicating like %:value%   or s.attorneyHandlingTheCase like %:value% " , nativeQuery = true)
    Page<Litigation> findByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCase(Pageable pageable, @Param("value") String value);
    @Query(value = "select * from litigation s where s.litigationType like %:value% or s.courtAdjudicating like %:value%   or s.attorneyHandlingTheCase like %:value% and  s.branchId  =:branchId" , nativeQuery = true)
    Page<Litigation> findByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseAndBranchId(Pageable pageable,@Param("value") String value,@Param("branchId") Long branchId);

    @Query(value = "select * from litigation s where s.litigationType like %:value% or s.courtAdjudicating like %:value%   or s.attorneyHandlingTheCase like %:value% and  s.attorney  =:attorney" , nativeQuery = true)
    Page<Litigation> findByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseAndAttorneyHandlingTheCase(Pageable pageable,@Param("value") String value,@Param("attorney") String attorney);

    @Query(value = "select * from litigation s where s.litigationType like %:value% or s.courtAdjudicating like %:value%   or s.attorneyHandlingTheCase like %:value% and  s.status  =:status" , nativeQuery = true)
    Page<Litigation> findByLitigationTypeOrCourtAdjudicatingOrAttorneyHandlingTheCaseAndStatus(Pageable pageable,@Param("value") String value,@Param("status") String status);



}
