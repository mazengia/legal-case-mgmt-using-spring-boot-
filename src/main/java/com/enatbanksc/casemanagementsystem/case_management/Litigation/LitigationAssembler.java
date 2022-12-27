package com.enatbanksc.casemanagementsystem.case_management.Litigation;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class LitigationAssembler implements RepresentationModelAssembler<Litigation, LitigationDto> {

    @Override
    public LitigationDto toModel(Litigation litigation) {
        return new LitigationDto(
                litigation.getLitigationId(),
                litigation.getFileNumber(),
                litigation.getCourtAdjudicating(),
                litigation.getLitigationType(),
                litigation.getIsBankPlaintiff(),
                litigation.getCaseStage(),
                litigation.getBranch(),
                litigation.getPlaintiff(),
                litigation.getDefendant(),
                litigation.getAttorneyHandlingTheCase(),
                litigation.getStatus(),
                litigation.getIntervene(),
                litigation.getAdvocate(),
                litigation.getCaseType(),
                litigation.getJudicialAppointment()
        );
    }


}

//            litigation.getDeletedAt(),
//            litigation.getDeletedBy(),
//            litigation.getCreatedBy(),
//            litigation.getUpdatedBy(),
//            litigation.getCreatedAt(),
//            litigation.getUpdatedAt(),
//            litigation.getRemark(),
//            litigation.getVersion()