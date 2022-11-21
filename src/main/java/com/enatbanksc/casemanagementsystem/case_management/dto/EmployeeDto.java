package com.enatbanksc.casemanagementsystem.case_management.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private String employeeId;
    private String fullName;
    private BranchDto branch;
}
