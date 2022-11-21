package com.enatbanksc.casemanagementsystem.case_management.config;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.CaseOwnerBranchDto;
import com.enatbanksc.casemanagementsystem.case_management.config.EmployeeFeignClientInterceptor;
import com.enatbanksc.casemanagementsystem.case_management.dto.BranchDto;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "caseOwnerBranchClient", configuration = EmployeeFeignClientInterceptor.class, url = "${services.hr}")
public interface CaseOwnerBranchClient {
    @GetMapping("/branches/{id}")
    CaseOwnerBranchDto getBranchById(@PathVariable("id") Long id);
}
