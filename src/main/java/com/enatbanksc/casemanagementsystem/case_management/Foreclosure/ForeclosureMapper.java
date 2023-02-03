package com.enatbanksc.casemanagementsystem.case_management.Foreclosure;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ForeclosureMapper {
    Foreclosure toForeclosure(ForeclosureDto foreclosureDto);

    ForeclosureDto toForeclosureDto(Foreclosure foreclosure);
}
