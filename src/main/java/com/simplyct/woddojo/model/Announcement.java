package com.simplyct.woddojo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cyril on 7/5/15.
 */
@Entity
@Data
public class Announcement {

    @Id
    @SequenceGenerator(name="seq_announcement", sequenceName="seq_announcement", schema="public", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_announcement")
    @Column(name = "announcement_id", unique = true)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;

    @Column(name = "content")
    String content;

    @Column(name = "title")
    String title;

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    Date createdDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    Date endDate;

}
