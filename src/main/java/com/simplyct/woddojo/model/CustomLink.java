package com.simplyct.woddojo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by cyril on 8/24/15.
 */
@Entity
@Data
public class CustomLink {

    @Id
    @SequenceGenerator(name = "seq_custom_link", sequenceName = "seq_custom_link", schema = "public", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_custom_link")
    @Column(name = "custom_link_id", unique = true)
    Long id;

    @Column(name = "link_name")
    String linkName;

    @Column(name = "link_url")
    String linkUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;
}
