package com.enatbanksc.casemanagementsystem.case_management.Executions.JudgmentCreditorGetter;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.Appeal;
import com.enatbanksc.casemanagementsystem.case_management.Executions.Executions;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "judgmentCreditorGetter")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE judgmentCreditorGetter SET deleted = 1 WHERE id=? and version=?")
public class JudgmentCreditorGetter extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appealApplicantRespondentId;
    private String judgmentCreditor;
    private String judgmentGetter;
    @ManyToOne(fetch = FetchType.EAGER  )
    @JoinColumn(name = "executionId",nullable = false)
    @JsonIgnoreProperties(value={"judgmentCreditorGetter"} )
    private Executions executions ;
}
