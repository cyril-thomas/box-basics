package com.simplyct.woddojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created by cyril on 6/15/15.
 */
@Entity
@Table(name = "organization")
@Data
public class Organization {
    @Id
    @SequenceGenerator(name="seq_organization", sequenceName="seq_organization", schema="public", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_organization")
    @Column(name = "org_id", unique = true)
    Long id;

    @Column(name = "name")
    @NotEmpty(message = "First Name is required")
    String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    Address postalAddress;

    @Column(name = "email")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Email address is required")
    String email;

    @Column(name = "phone")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "Phone number is required")
    String phone;

    @Column(name = "web_domain")
    String webDomain;

    @Column(name = "hash_tag")
    String hashTag;

    @Column(name = "hours")
    String hours;
}
