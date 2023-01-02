package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff.DefendantPlaintiff;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff.DefendantPlaintiffDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppealApplicantRespondentMapper {
    AppealApplicantRespondent toAdvocate(AppealApplicantRespondentDto appealApplicantRespondentDto);
    AppealApplicantRespondentDto toAdvocateDto(AppealApplicantRespondent appealApplicantRespondent);


    List<AppealApplicantRespondentDto> toAppealApplicantRespondentDto(List<AppealApplicantRespondent> appealApplicantRespondents);
    List<AppealApplicantRespondent> toAppealApplicantRespondent( List<AppealApplicantRespondentDto> appealApplicantRespondentDtos);

}
