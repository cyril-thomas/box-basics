package com.simplyct.woddojo.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by cyril on 7/5/15.
 */
@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "status")
    String status;

    @Column(name = "customer_id")
    String customerId;

    @Column(name = "confirmation_id")
    String confirmationId;

    @Transient
    String nonce;
}
