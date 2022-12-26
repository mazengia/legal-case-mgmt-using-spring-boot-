//package com.enatbanksc.casemanagementsystem.case_management.SentEmail;
//
//
//import com.enatbanksc.casemanagementsystem.case_management.CaseType.CaseType;
//import com.enatbanksc.casemanagementsystem.case_management.ForeClosure.ForeClosure;
//import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.*;
//import org.hibernate.Hibernate;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
//@Entity
//@Table(name = "emailDetails")
//
//public class EmailDetails extends Auditable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String recipient;
//    private String msgBody;
//    private String subject;
//    private String attachment;
//    private boolean sent;
//    @OneToOne (fetch = FetchType.EAGER,optional = false)
//    @JoinColumn(name = "foreClosure_id",nullable = false)
//    @JsonIgnoreProperties(value={"emailDetails"} )
//    private ForeClosure foreClosure;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        EmailDetails that = (EmailDetails) o;
//        return id != null && Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}
