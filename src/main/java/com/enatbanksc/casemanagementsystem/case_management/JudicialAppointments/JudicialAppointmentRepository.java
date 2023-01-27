package com.enatbanksc.casemanagementsystem.case_management.JudicialAppointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface JudicialAppointmentRepository extends PagingAndSortingRepository<JudicialAppointment, Long>, JpaSpecificationExecutor<JudicialAppointment> {
    Page<JudicialAppointment> findJudicialAppointmentByLitigationLitigationIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<JudicialAppointment> findAllByDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    Page<JudicialAppointment> findJudicialAppointmentByExecutionsExecutionsIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<JudicialAppointment> findJudicialAppointmentByExecutionsAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<JudicialAppointment> findJudicialAppointmentByLitigationAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<JudicialAppointment> findAllByLitigationLitigationIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    Page<JudicialAppointment> findAllByExecutionsExecutionsIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);


    //    Page<JudicialAppointment> findJudicialAppointmentByLitigationExists
//    Page<JudicialAppointment> findJudicialAppointmentByExecutionsExists
}
