package edu.simplyct.boxbasics.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/29/15.
 */
@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroTitle() {
        return introTitle;
    }

    public void setIntroTitle(String introTitle) {
        this.introTitle = introTitle;
    }

    public String getIntroContent() {
        return introContent;
    }

    public void setIntroContent(String introContent) {
        this.introContent = introContent;
    }

    public String getServicesTitle() {
        return servicesTitle;
    }

    public void setServicesTitle(String servicesTitle) {
        this.servicesTitle = servicesTitle;
    }

    public String getServicesContent() {
        return servicesContent;
    }

    public void setServicesContent(String servicesContent) {
        this.servicesContent = servicesContent;
    }

    public String getRegTitle() {
        return regTitle;
    }

    public void setRegTitle(String regTitle) {
        this.regTitle = regTitle;
    }

    public String getRegContent() {
        return regContent;
    }

    public void setRegContent(String regContent) {
        this.regContent = regContent;
    }

    public String getRegBanner() {
        return regBanner;
    }

    public void setRegBanner(String regBanner) {
        this.regBanner = regBanner;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
