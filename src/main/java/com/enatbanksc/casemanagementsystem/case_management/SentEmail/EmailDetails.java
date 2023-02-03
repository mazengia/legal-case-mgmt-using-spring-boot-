package com.enatbanksc.casemanagementsystem.case_management.SentEmail;


import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management.Foreclosure.Foreclosure;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "emailDetails")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE emailDetails SET deleted = 'true' WHERE id=? and version=?")

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
    private Foreclosure foreclosure;
    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "litigationId",nullable = true)
    @JsonIgnoreProperties(value={"emailDetails"} )
    private Litigation litigation;
    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "executionsId",nullable = true)
    @JsonIgnoreProperties(value={"emailDetails"} )
    private Executions executions;

}
