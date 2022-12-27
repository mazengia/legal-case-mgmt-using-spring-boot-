package com.enatbanksc.casemanagementsystem.case_management.SentEmail;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailDetails toEmailDetails(EmailDto emailDto);
    EmailDto toMEmailDto(EmailDetails emailDetails);
}
