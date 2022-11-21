package com.enatbanksc.casemanagementsystem.case_management.settings.CaseType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CaseTypeMapper {
    CaseType toCaseType(CaseTypeDto caseTypeDto);
    CaseTypeDto toCaseTypeDto(CaseType caseType);
}
