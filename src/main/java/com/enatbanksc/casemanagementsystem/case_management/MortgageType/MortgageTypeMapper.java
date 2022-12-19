package com.enatbanksc.casemanagementsystem.case_management.MortgageType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageTypeMapper {
    MortgageType toMortgageType(MortgageTypeDto mortgageTypeDto);
    MortgageTypeDto toMortgageTypeDto(MortgageType mortgageType);
}
