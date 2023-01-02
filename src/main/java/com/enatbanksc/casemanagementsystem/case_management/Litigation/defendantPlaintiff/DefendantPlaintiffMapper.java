package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefendantPlaintiffMapper {
    DefendantPlaintiff toAdvocate(DefendantPlaintiffDto defendantPlaintiffDto);
    DefendantPlaintiffDto toAdvocateDto(DefendantPlaintiff defendantPlaintiff);
    List<DefendantPlaintiffDto> toDefendantPlaintiffDto(List<DefendantPlaintiff> defendantPlaintiff);
    List<DefendantPlaintiff> toDefendantPlaintiff( List<DefendantPlaintiffDto> defendantPlaintiffDto);
}
