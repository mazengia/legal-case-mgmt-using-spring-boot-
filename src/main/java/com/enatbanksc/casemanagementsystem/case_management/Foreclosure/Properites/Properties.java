package com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Properites;

import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name = "properties")
@Table(name = "properties")
@Data

@SQLDelete(sql = "UPDATE properties SET deleted = 'true'  WHERE id=? and version=?")
@Where(clause = "deleted=false")
public class Properties extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String machineryType;
    private String numberOfTitleIndeed;
    private String plateNumber;
    private String shansiNumber;
    private String motorNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "foreclosureId",nullable = false)
    @JsonIgnoreProperties(value={"properties"} )
    private Foreclosure foreclosure;
}
