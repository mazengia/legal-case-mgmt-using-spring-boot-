package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ExpenseDetailRepository  extends PagingAndSortingRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    Page<Expense> findExpenseDetailByLitigationLitigationIdOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<Expense> findExpenseDetailByExecutionsExecutionsIdOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<Expense> findExpenseDetailByMortgageDetailMortgageDetailIdOrderByCreatedAtDesc(Pageable pageable, Long id);

    Page<Expense> findAllByMortgageDetailMortgageDetailIdNotNullOrderByCreatedAtDesc(Pageable pageable);
    Page<Expense> findAllByLitigationLitigationIdNotNullOrderByCreatedAtDesc(Pageable pageable);
   Page<Expense> findAllByExecutionsExecutionsIdNotNullOrderByCreatedAtDesc(Pageable pageable);
    Page<Expense> findAllByExecutionsAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<Expense> findAllByLitigationAttorneyHandlingTheCaseOrderByCreatedAtDesc(Pageable pageable, String attorney);

}
