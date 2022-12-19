package com.enatbanksc.casemanagementsystem.case_management.Comment;

import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class CommentDto extends Auditable {
    private Long commentId;
    private String content;
    private LitigationEmployee sender;
    @JsonBackReference
    private Litigation litigation;
    @JsonBackReference
    private JudiciaryReport judiciaryReport;
}
