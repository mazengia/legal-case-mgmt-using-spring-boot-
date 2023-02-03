package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;

 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface PropertiesRepository extends PagingAndSortingRepository<Properties, Long>, JpaSpecificationExecutor<Properties> {
    Page<Properties> findAllByDeletedIsFalseOrderByIdDesc(Pageable pageable);
     Page<Properties> findPropertiesByForeclosureForeclosureIdAndDeletedIsFalseOrderByIdDesc(Pageable pageable, long id);

}
