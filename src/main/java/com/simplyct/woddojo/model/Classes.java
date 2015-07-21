package com.simplyct.woddojo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by cyril on 7/5/15.
 */
@Entity
@Data
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "class_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_id")
    Coach coach;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

}
