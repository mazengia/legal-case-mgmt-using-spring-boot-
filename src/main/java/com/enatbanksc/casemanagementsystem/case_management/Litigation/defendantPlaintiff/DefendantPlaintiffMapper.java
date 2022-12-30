package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DefendantPlaintiffMapper {
    DefendantPlaintiff toAdvocate(DefendantPlaintiffDto defendantPlaintiffDto);
    DefendantPlaintiffDto toAdvocateDto(DefendantPlaintiff defendantPlaintiff);
}
