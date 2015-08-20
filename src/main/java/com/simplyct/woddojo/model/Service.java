package com.simplyct.woddojo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by cyril on 7/5/15.
 */
@Entity
@Data
public class Service {

    @Id
    @SequenceGenerator(name="seq_service", sequenceName="seq_service", schema="public", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_service")
    @Column(name = "service_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;

    @Column(name = "content")
    String content;

    @Column(name = "title")
    String title;

}
