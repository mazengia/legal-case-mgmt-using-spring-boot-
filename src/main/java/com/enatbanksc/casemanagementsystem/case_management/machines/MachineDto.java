package com.enatbanksc.casemanagementsystem.case_management.machines;

import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class MachineDto extends Auditable {
    private Long id;
    private String machineryType;
    private Foreclosure foreclosure;
}
