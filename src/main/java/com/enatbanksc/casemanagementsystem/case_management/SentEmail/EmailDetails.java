package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.MortgageType.MortgageDetail.MortgageDetail;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "emailDetails")

public class EmailDetails extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private boolean sent;
    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "mortgageDetailId",nullable = true)
    @JsonIgnoreProperties(value={"emailDetails"} )
    private MortgageDetail mortgageDetail;
    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "litigationId",nullable = true)
    @JsonIgnoreProperties(value={"emailDetails"} )
    private Litigation litigation;

}
