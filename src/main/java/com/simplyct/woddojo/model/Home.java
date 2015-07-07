package com.simplyct.woddojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/29/15.
 */
@Entity
@Data
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "home_id", unique = true)
    Long id;

    @Column(name = "title")
    @NotEmpty(message = "Title for home page is required")
    String title;

    @Column(name = "intro_title")
    String introTitle;

    @Column(name = "intro_content")
    String introContent;

    @Column(name = "services_title")
    String servicesTitle;

    @Column(name = "services_content")
    String servicesContent;

    @Column(name = "registration_title")
    String regTitle;

    @Column(name = "registration_content")
    String regContent;

    @Column(name = "registration_banner")
    String regBanner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;
}
