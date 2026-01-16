package org.chandlercasey.easybank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter @Setter
public class Accounts {

    @Column(name = "customer_id")
    private long customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_number")
    private long accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="account_type")
    private AccountTypes accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private Date createDt;

}