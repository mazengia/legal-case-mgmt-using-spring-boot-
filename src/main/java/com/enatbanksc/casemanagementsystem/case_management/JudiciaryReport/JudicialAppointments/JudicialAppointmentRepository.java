package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudicialAppointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface JudicialAppointmentRepository extends PagingAndSortingRepository<JudicialAppointment, Long>, JpaSpecificationExecutor<JudicialAppointment> {
    Page<JudicialAppointment> findJudicialAppointmentByLitigationLitigationIdOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<JudicialAppointment> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<JudicialAppointment> findJudicialAppointmentByExecutionsExecutionsIdOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<JudicialAppointment> findJudicialAppointmentByExecutionsAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<JudicialAppointment> findJudicialAppointmentByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<JudicialAppointment> findAllByLitigationLitigationIdNotNullOrderByCreatedAtDesc(Pageable pageable);
    Page<JudicialAppointment> findAllByExecutionsExecutionsIdNotNullOrderByCreatedAtDesc(Pageable pageable);


    //    Page<JudicialAppointment> findJudicialAppointmentByLitigationExists
//    Page<JudicialAppointment> findJudicialAppointmentByExecutionsExists
}
