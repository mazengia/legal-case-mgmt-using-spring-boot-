package com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.Adjournment;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdjournmentMapper {
    Adjournment toAdjournment (AdjournmentDto adjournmentDto);
    AdjournmentDto toAdjournmentDto(Adjournment adjournment);
}
