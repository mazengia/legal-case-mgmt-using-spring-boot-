package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.LitigationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LitigationMapper {
    Litigation toLitigation(LitigationDto litigationDto);
    LitigationDto toLitigationDto(Litigation litigation);
}
