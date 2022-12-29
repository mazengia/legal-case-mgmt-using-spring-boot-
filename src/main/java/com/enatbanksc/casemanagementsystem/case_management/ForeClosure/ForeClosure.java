package com.enatbanksc.casemanagementsystem.case_management.ForeClosure;

import com.enatbanksc.casemanagementsystem.case_management._EmbeddedClasses.Employee;
import com.enatbanksc.casemanagementsystem.case_management._config.utils.Auditable;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity(name = "ForeClosure")
@Table(name = "foreclosure")
@Data
@Where(clause = "deleted=0")
@SQLDelete(sql = "UPDATE foreclosure SET deleted = 1 WHERE id=? and version=?")
public class ForeClosure extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foreClosureId;
    private String status;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "employeeId", column = @Column(name = "maintainer_employee_id")),
            @AttributeOverride(name = "fullName", column = @Column(name = "maintainer_employee_fullName")),
            @AttributeOverride(name = "branch.code", column = @Column(name = "maintainer_branch_code")),
            @AttributeOverride(name = "branch.name", column = @Column(name = "maintainer_branch_name"))
    })
    private Employee maintained_by;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "mortgageDetail_id", nullable = false, unique = true)
//    @JsonIgnoreProperties(value = {"foreClosure"})
//    private MortgageDetail mortgageDetail;

//    @OneToMany(mappedBy = "foreClosure")
//    private List<AuctionType> AuctionType;


}
