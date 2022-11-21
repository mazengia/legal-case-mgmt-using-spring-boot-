package com.enatbanksc.casemanagementsystem.case_management.Litigation.Advocate;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvocateMapper {
    Advocate toAdvocate(AdvocateDto advocateDto);
    AdvocateDto toAdvocateDto(Advocate advocate);
}
