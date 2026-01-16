package org.chandlercasey.easybank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.chandlercasey.easybank.entities.dto.CardType;

import java.sql.Date;

@Entity
@Getter @Setter
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "card_id")
    private long cardId;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;

    @Column(name = "total_limit")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

    @Column(name = "available_amount")
    private int availableAmount;

    @Column(name = "create_dt")
    private Date createDt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_number", nullable = false)
    private Account account;


}