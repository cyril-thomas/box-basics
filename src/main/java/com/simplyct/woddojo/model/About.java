package com.simplyct.woddojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/29/15.
 */
@Entity
@Data
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "about_id", unique = true)
    Long id;

    @Column(name = "about_title")
    @NotEmpty(message = "Title for about page is required")
    String title;

    @Column(name = "about_content")
    String aboutContent;

    @Column(name = "facebook_url")
    String facebookLink;

    @Column(name = "twitter_url")
    String twitterLink;

    @Column(name = "google_plus_url")
    String gPlusLink;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;
}
