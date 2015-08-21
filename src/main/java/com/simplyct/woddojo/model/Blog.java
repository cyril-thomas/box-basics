package com.simplyct.woddojo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cyril on 8/20/15.
 */
@Entity
@Data
public class Blog {

    @Id
    @SequenceGenerator(name = "seq_blog", sequenceName = "seq_blog", schema = "public", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_blog")
    @Column(name = "blog_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_id")
    Coach coach;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "post_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date postDate;
}
