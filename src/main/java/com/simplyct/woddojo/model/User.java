package com.simplyct.woddojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by cyril on 5/25/15.
 */
@Entity
@Data
public class User {

    @Id
    @SequenceGenerator(name = "seq_user", sequenceName = "seq_user", schema = "public", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @Column(name = "user_id", unique = true)
    Long id;


    @Column(name = "user_phone")
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
            message = "Phone number is required to reach you in case of emergency")
    protected String phone;

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

    @Column(name = "dob")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @Column(name = "city")
    String city;

    @Column(name = "zip")
    @Length(max = 5, min = 5,message = "Zip code must be a valid 5 digit code")
    String zip;

    @Column(name = "gender")
    @Length(max = 1, min = 1)
    String gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    Organization organization;
}
