package com.enatbanksc.casemanagementsystem.case_management.Litigation.Intervene;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InterveneMapper {
    Intervene toEntervene(InterveneDto interveneDto);
    InterveneDto toInterveneDto(Intervene intervene);
    List<InterveneDto> toInterveneDto(List<Intervene> intervenes);
    List<Intervene> toIntervene(List<InterveneDto> interveneDtos);
}
