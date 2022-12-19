package com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MortgageDetailMapper {
    MortgageDetail toMortgageDetail(MortgageDetailDto mortgageDetailDto);

    MortgageDetailDto toMortgageDetailDto(MortgageDetail mortgageDetail);
}
