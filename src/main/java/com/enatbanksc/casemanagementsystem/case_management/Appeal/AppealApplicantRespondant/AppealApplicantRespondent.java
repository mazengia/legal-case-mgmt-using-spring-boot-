package com.enatbanksc.casemanagementsystem.case_management.Appeal.AppealApplicantRespondant;

import com.enatbanksc.casemanagementsystem.case_management.Appeal.Appeal;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "appealApplicantRespondent")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE appealApplicantRespondent SET deleted = 1 WHERE id=? and version=?")
public class AppealApplicantRespondent extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appealApplicantRespondentId;
    private String applicant;
    private String respondent;
    //    @JsonManagedReference
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "appealId", nullable = false)
    @JsonIgnoreProperties(value = {"appealApplicantRespondent"})
    private Appeal appeal;
}
