package edu.simplyct.boxbasics.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by cyril on 6/15/15.
 */
@Entity
@Table(name = "address")
@Data
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
