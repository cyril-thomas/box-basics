package com.simplyct.woddojo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by cyril on 8/20/15.
 */
@Entity
@Data
public class Pricing {

    @Id
    @SequenceGenerator(name = "seq_pricing", sequenceName = "seq_pricing", schema = "public", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pricing")
    @Column(name = "price_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;

    @Column(name = "membership_level")
    String level;

    @Column(name = "cost", length = 16000)
    BigDecimal cost;
}
