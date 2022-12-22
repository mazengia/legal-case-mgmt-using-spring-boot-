package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments.JudicialAppointment;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Branch;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management._config.Common.CaseStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface LitigationRepository extends PagingAndSortingRepository<Litigation, Long>, JpaSpecificationExecutor<Litigation> {
    Page<Litigation> findLitigationByCaseStage(Pageable pageable, CaseStage caseStage);

    Page<Litigation> findLitigationByBranchId(Pageable pageable, Long branchId);
    Page<Litigation> findLitigationByAttorneyHandlingTheCase(Pageable pageable, String attorney);
    Page<Litigation> findLitigationByStatus(Pageable pageable, String Status);

}
