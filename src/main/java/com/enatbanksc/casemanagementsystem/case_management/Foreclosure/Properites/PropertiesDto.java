package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;

import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;

@Data
public class PropertiesDto  extends Auditable {
    private Long id;
    private String machineryType;
    private String numberOfTitleIndeed;
    private String plateNumber;
    private String shansiNumber;
    private String motorNumber;
    private Foreclosure Foreclosure;

}