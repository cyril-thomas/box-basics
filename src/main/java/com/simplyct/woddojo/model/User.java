package com.simplyct.woddojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * Created by cyril on 5/25/15.
 */
@Entity
@Table(name = "member")
@Data
public class User {

    @Column(name = "user_phone")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "Phone number is required to reach you in case of emergency")
    protected String phone;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", unique = true)
    Long id;
    @Column(name = "first_name")
    @NotEmpty(message = "First Name is required")
    String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last Name is required")
    String lastName;
    @Column(name = "user_email")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Email address is required to contact you")
    String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    Organization organization;
}
