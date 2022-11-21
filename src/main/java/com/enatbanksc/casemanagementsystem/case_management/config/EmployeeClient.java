package com.enatbanksc.casemanagementsystem.case_management.config;


import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeDto;
import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.LitigationEmployee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "employee-service",url = "${services.hr}")
public interface EmployeeClient {
    @GetMapping("/employees/by-employeeId/{employeeId}")
    EmployeeDto getEmployeeById(@PathVariable("employeeId") String employeeId);

    @GetMapping("/employees/by-employeeId/{employeeId}")
    LitigationEmployee getEmployee(@PathVariable("employeeId") String employeeId);
}
