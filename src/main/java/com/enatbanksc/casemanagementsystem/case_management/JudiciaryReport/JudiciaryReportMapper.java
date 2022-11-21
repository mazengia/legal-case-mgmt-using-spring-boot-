package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JudiciaryReportMapper {
    JudiciaryReport toJudiciaryReport(JudiciaryReportDto judiciaryReportDto);
    JudiciaryReportDto toJudiciaryReportDto(JudiciaryReport JudiciaryReport);
}
