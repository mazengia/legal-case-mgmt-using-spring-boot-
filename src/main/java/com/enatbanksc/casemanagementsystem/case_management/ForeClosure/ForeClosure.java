package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name = "ForeClosure")
@Table(name="foreclosure")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE foreclosure SET deleted = 1 WHERE id=? and version=?")
public class ForeClosure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foreClosureId;
    private String mortgagor;

}
