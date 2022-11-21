package com.enatbanksc.casemanagementsystem.case_management.settings.MortgageType.MortgageDetail;

import com.enatbanksc.casemanagementsystem.case_management.settings.MortgageType.MortgageType;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name="MortgageDetail")
@Table(name="mortgage_details")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE mortgage_details SET deleted = 1 WHERE id=? and version=?")
public class MortgageDetail extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mortgageDetailId;
    private String plateNumber;
    private String shansiNumber;
    private String motorNumber;
    private String machineryType;
    private String numberOfTitleIndeed;
    private String dateCollateralRegistered;
    @ManyToOne
    @JoinColumn(name="mortgage_type_id", nullable=false)
    private MortgageType mortgageType;
}
