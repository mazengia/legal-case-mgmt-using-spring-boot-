package com.enatbanksc.casemanagementsystem.case_management.Expense.ExpenseDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ExpenseDetailRepository  extends PagingAndSortingRepository<ExpenseDetail, Long>, JpaSpecificationExecutor<ExpenseDetail> {

    Page<ExpenseDetail> findExpenseDetailByJudicialAppointmentAppointmentId(Pageable pageable, Long id);

}
