package com.enatbanksc.casemanagementsystem.case_management.Files;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilesMapper {
    Files toFiles(FilesDto filesDto);
    FilesDto toFilesDto(Files files);
}
