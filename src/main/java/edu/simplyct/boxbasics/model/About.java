package edu.simplyct.boxbasics.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/29/15.
 */
@Entity
@Data
@ToString
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
}
