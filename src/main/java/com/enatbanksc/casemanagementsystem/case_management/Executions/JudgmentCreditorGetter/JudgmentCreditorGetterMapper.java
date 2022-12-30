package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JudgmentCreditorGetterMapper {
    JudgmentCreditorGetter toAdvocate(JudgmentCreditorGetterDro judgmentCreditorGetterDro);
    JudgmentCreditorGetterDro toAdvocateDto(JudgmentCreditorGetter judgmentCreditorGetter);
}
