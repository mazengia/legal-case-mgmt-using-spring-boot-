package com.enatbanksc.casemanagementsystem.case_management.Expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ExpenseDetailRepository  extends PagingAndSortingRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    Page<Expense> findExpenseDetailByLitigationLitigationIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<Expense> findExpenseDetailByExecutionsExecutionsIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<Expense> findExpenseDetailByForeclosureForeclosureIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);

    Page<Expense> findAllByForeclosureForeclosureIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    Page<Expense> findAllByLitigationLitigationIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
   Page<Expense> findAllByExecutionsExecutionsIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    Page<Expense> findAllByExecutionsAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<Expense> findAllByLitigationAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String attorney);

}
