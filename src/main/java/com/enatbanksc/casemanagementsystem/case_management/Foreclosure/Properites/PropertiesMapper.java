package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;


import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PropertiesMapper {
    Properties toProperties(PropertiesDto propertiesDto);
    Iterable<Properties> toPropertiesArray(Iterable<PropertiesDto> propertiesDto);
    List<Properties> toPropertiesList(List<PropertiesDto> propertiesDto);

    PropertiesDto toPropertiesDto(Properties properties);
    Iterable<PropertiesDto> toPropertiesArrayDto(Iterable<Properties> properties);

    List<PropertiesDto> toPropertiesDtoList(List<Properties> updateProperties);
}
