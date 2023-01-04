package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JudgmentCreditorGetterMapper {
    JudgmentCreditorGetter toAdvocate(JudgmentCreditorGetterDto judgmentCreditorGetterDto);
    JudgmentCreditorGetterDto toAdvocateDto(JudgmentCreditorGetter judgmentCreditorGetter);

    List<JudgmentCreditorGetterDto> toJudgmentCreditorGetterDto(List<JudgmentCreditorGetter> judgmentCreditorGetters);
    List<JudgmentCreditorGetter> toJudgmentCreditorGetter( List<JudgmentCreditorGetterDto> judgmentCreditorGetterDtos);

}
