package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import com.enatbanksc.casemanagementsystem.case_management.AuctionType.AuctionType;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ForeClosure")
@Table(name = "foreclosure")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE foreclosure SET deleted = 1 WHERE id=? and version=?")
public class ForeClosure  extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foreClosureId;
    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mortgageDetail_id", nullable = false, unique = true)
    @JsonIgnoreProperties(value = {"foreClosure"})
    private MortgageDetail mortgageDetail;

    @OneToMany(mappedBy = "foreClosure")
    private List<AuctionType> AuctionType;


}
