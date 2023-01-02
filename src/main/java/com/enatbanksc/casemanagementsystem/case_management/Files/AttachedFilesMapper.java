package com.enatbanksc.casemanagementsystem.case_management.Files;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttachedFilesMapper {
    AttachedFiles toFiles(AttachedFilesDto attachedFilesDto);
    AttachedFilesDto toFilesDto(AttachedFiles attachedFiles);
}
