package com.simplyct.woddojo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cyril on 9/1/15.
 */
@Entity
@Data
public class Comment {
    @Id
    @SequenceGenerator(name = "seq_comment", sequenceName = "seq_comment", schema = "public", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comment")
    @Column(name = "comment_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    Blog blog;

    @Column(name = "content")
    String content;

    @Column(name = "post_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date postDate;
}
