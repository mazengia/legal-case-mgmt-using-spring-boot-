package com.enatbanksc.casemanagementsystem.case_management.ExpenseDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface ExpenseDetailRepository  extends PagingAndSortingRepository<ExpenseDetail, Long>, JpaSpecificationExecutor<ExpenseDetail> {

    Page<ExpenseDetail> findExpenseDetailByLitigationLitigationIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<ExpenseDetail> findExpenseDetailByExecutionsExecutionsIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);
    Page<ExpenseDetail> findExpenseDetailByMortgageDetailMortgageDetailIdAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, Long id);

    Page<ExpenseDetail> findAllByMortgageDetailMortgageDetailIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    Page<ExpenseDetail> findAllByLitigationLitigationIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
   Page<ExpenseDetail> findAllByExecutionsExecutionsIdNotNullAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    Page<ExpenseDetail> findAllByExecutionsAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String attorney);
    Page<ExpenseDetail> findAllByLitigationAttorneyHandlingTheCaseAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable, String attorney);

}
