package com.enatbanksc.casemanagementsystem.case_management.Comment;

import com.enatbanksc.casemanagementsystem.case_management.EmbeddedClasses.LitigationEmployee;
import com.enatbanksc.casemanagementsystem.case_management.JudiciaryReport.JudiciaryReport;
import com.enatbanksc.casemanagementsystem.case_management.Litigation.Litigation;
import com.enatbanksc.casemanagementsystem.case_management.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "sender_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "sender_employee_fullName")),
            @AttributeOverride(name = "contact.email", column = @Column(name = "sender_email")),
    })
    private LitigationEmployee sender;

    @ManyToOne()
    @JoinColumn(name = "litigation_id", nullable = false)
    private Litigation litigation;


    @ManyToOne
    @JoinColumn(name = "judiciaryReport_id", nullable = false)
    private JudiciaryReport judiciaryReport;

}
