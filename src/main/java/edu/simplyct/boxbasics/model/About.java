package edu.simplyct.boxbasics.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/29/15.
 */
@Entity
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

    public String getAboutContent() {
        return aboutContent;
    }

    public void setAboutContent(String aboutContent) {
        this.aboutContent = aboutContent;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
