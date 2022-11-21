package com.enatbanksc.casemanagementsystem.case_management.Common;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Address implements Serializable {
    private String state;
    private String city;
    private String subCity;
    private String woredaOrKebele;
    //    private String street;
    private String houseNo;
}
