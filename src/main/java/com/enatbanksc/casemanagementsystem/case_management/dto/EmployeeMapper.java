package com.enatbanksc.casemanagementsystem.case_management.dto;


import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}