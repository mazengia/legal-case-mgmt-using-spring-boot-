package com.enatbanksc.casemanagementsystem.case_management.machines;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface MachineRepository extends PagingAndSortingRepository<MachineType, Long>, JpaSpecificationExecutor<MachineType> {
    Page<MachineType> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
