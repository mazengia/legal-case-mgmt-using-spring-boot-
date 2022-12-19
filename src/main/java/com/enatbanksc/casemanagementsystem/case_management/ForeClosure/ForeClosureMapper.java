package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ForeClosureMapper {
    ForeClosure toForeClosure(ForeClosureDto foreClosureDto);
    ForeClosureDto toForeClosureDto(ForeClosure foreClosure);
}
