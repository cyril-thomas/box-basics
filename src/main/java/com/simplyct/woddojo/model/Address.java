package com.simplyct.woddojo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by cyril on 6/15/15.
 */
@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @SequenceGenerator(name="seq_address", sequenceName="seq_address", schema="public", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_address")
    @Column(name = "address_id", unique = true)
    Long id;

    @Column(name = "first_line")
    String firstLine;

    @Column(name = "second_line")
    String secondLine;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "zip_code")
    String zipcode;
}
