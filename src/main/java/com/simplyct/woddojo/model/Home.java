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
    @SequenceGenerator(name="seq_home", sequenceName="seq_home", schema="public", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_home")
    @Column(name = "home_id", unique = true)
    Long id;

    @Column(name = "title", length = 4096)
    @NotEmpty(message = "Title for home page is required")
    String title;

    @Column(name = "intro_content", length = 4096)
    String introContent;

    @Column(name = "services_title", length = 4096)
    String servicesTitle;

    @Column(name = "registration_title", length = 4096)
    String regTitle;

    @Column(name = "registration_content", length = 4096)
    String regContent;

    @Column(name = "registration_banner", length = 4096)
    String regBanner;

    @Column(name = "video_url")
    String videoUrl;


    @Column(name = "logo_url")
    String logoUrl;


    @Column(name = "bg_url")
    String bgUrl;


    @Column(name = "alt_bg_url")
    String altBgUrl;

    @Column(name = "custom_css", length = 16000)
    String css;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;
}
