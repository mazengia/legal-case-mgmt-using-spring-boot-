package com.enatbanksc.casemanagementsystem.case_management.machines;

import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "machineType")
@Data
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE machineType SET deleted = 'true' WHERE id=? and version=?")
public class MachineType extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String machineryType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mortgageDetailId")
    @JsonIgnoreProperties(value = {"machineType"})
    private Foreclosure foreclosure;
}
