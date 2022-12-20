package com.enatbanksc.casemanagementsystem.case_management.Comment;

import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name="Comment")
@Table(name="comments")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE comments SET deleted = 1 WHERE id=? and version=?")
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String content;
    @ManyToOne(fetch = FetchType.EAGER, optional = false )
    @JoinColumn(name = "litigation_id")
    @JsonIgnoreProperties(value={"comment"} )
    private Litigation litigation;

}
