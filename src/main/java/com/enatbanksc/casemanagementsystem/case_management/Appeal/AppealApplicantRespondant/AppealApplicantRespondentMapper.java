package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppealApplicantRespondentMapper {
    AppealApplicantRespondent toAppealApplicant(AppealApplicantRespondentDto appealApplicantRespondentDto);
    AppealApplicantRespondentDto toAppealApplicantDto(AppealApplicantRespondent appealApplicantRespondent);


    List<AppealApplicantRespondentDto> toAppealApplicantRespondentDto(List<AppealApplicantRespondent> appealApplicantRespondents);
    List<AppealApplicantRespondent> toAppealApplicantRespondent( List<AppealApplicantRespondentDto> appealApplicantRespondentDtos);

}
