package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LitigationMapper {
    Litigation toLitigation(LitigationDto litigationDto);
    LitigationDto toLitigationDto(Litigation litigation);
}
