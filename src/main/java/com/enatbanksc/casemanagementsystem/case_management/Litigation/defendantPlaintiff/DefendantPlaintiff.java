package com.enatbanksc.casemanagementsystem.case_management.Litigation.defendantPlaintiff;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "defendantPlaintiff")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE defendantPlaintiff SET deleted = 1 WHERE id=? and version=?")
public class DefendantPlaintiff extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long defendantPlaintiffId;
    private String plaintiff;
    private String defendant;
    @ManyToOne(fetch = FetchType.EAGER  )
    @JoinColumn(name = "litigationId",nullable = false)
    @JsonIgnoreProperties(value={"defendantPlaintiff"} )
    private Litigation litigation ;
}
