package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForeClosureDto extends Auditable {
    private Long foreClosureId;
    private String status;
    private Employee maintained_by;
//    private MortgageDetail mortgageDetail;
//    private List<AuctionType> AuctionType;
}