package com.enatbanksc.casemanagementsystem.case_management.Executions;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExecutionsMapper {
    Executions toLitigation(ExecutionsDto executionsDto);
    ExecutionsDto toLitigationDto(Executions executions);
}
