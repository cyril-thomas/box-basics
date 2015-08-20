package com.simplyct.woddojo.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by cyril on 6/30/15.
 */
@Entity
@Data
public class Coach {

    @Id
    @SequenceGenerator(name="seq_coach", sequenceName="seq_coach", schema="public", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_coach")
    @Column(name = "coach_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "description")
    String description;

    @Column(name = "job_title")
    String jobTitle;

    @Column(name = "profile_pic")
    String profilePic;


    @Column(name = "facebook_url")
    String facebookLink;

    @Column(name = "twitter_url")
    String twitterLink;

    @Column(name = "linkedIn")
    String linkedIn;
}
