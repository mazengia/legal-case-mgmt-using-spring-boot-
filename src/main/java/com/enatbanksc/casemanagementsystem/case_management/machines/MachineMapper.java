package com.enatbanksc.casemanagementsystem.case_management.machines;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MachineMapper {
    MachineType toCaseType(MachineDto machineDto);
    MachineDto toCaseTypeDto(MachineType machineType);
}
