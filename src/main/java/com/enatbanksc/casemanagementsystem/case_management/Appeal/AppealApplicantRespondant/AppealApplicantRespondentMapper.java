package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppealApplicantRespondentMapper {
    AppealApplicantRespondent toAdvocate(AppealApplicantRespondentDto appealApplicantRespondentDto);
    AppealApplicantRespondentDto toAdvocateDto(AppealApplicantRespondent appealApplicantRespondent);
}
