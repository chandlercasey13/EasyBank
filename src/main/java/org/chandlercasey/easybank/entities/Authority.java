package org.chandlercasey.easybank.entities;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;

    // Multiple Authorities can be assigned to a single Customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



}
