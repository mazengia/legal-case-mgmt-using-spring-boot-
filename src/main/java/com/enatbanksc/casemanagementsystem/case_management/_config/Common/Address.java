package com.enatbanksc.casemanagementsystem.case_management._config.Common;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address   {
    private String state;
    private String city;
    private String subCity;
    private String woredaOrKebele;
    //    private String street;
    private String houseNo;
}
