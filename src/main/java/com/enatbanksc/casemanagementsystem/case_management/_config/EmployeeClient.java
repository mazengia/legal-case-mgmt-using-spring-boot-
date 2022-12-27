package com.enatbanksc.casemanagementsystem.case_management._config;


import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "employee-service",url = "${services.hr}")
public interface EmployeeClient {
    @GetMapping("/employees/by-employeeId/{employeeId}")
    EmployeeDto getEmployeeById(@PathVariable("employeeId") String employeeId);

    @GetMapping("/employees/by-employeeId/{employeeId}")
    LitigationEmployee getEmployee(@PathVariable("employeeId") String employeeId);
    @GetMapping("/employees")
    LitigationEmployee getEmployees();

    @GetMapping("/employees/search?search=status:ACTIVE")
    List<Employee> getActiveEmployees();

    @GetMapping("/employees/team")
    LitigationEmployee getEmployeesByTeam();
}
