package com.enatbanksc.casemanagementsystem.case_management.Intervene;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterveneMapper {
    Intervene toIntervene(InterveneDto interveneDto);
    InterveneDto toInterveneDto(Intervene intervene);
}
