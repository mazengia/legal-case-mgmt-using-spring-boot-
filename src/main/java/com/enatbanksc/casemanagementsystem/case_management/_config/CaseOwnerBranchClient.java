package com.enatbanksc.casemanagementsystem.case_management._config;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.CaseOwnerBranchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "caseOwnerBranchClient", configuration = EmployeeFeignClientInterceptor.class, url = "${services.hr}")
public interface CaseOwnerBranchClient {
    @GetMapping("/branches/{id}")
    CaseOwnerBranchDto getBranchById(@PathVariable("id") Long id);
}
