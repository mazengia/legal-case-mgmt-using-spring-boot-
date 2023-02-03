package com.enatbanksc.casemanagementsystem.case_management.Files;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.Appeal;
import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class FilesDto extends Auditable {
    private Long fileId;
    private String fileName;
    private Litigation litigation;
    private Foreclosure foreclosure;
    private Appeal appeal;
    private Executions executions;
    private Employee maintained_by;
}
