package com.simplyct.woddojo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by cyril on 7/11/15.
 */
@Entity
@Data
public class Schedule {
    @Id
    @SequenceGenerator(name="seq_schedule", sequenceName="seq_schedule", schema="public", initialValue=1, allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_schedule")
    @Column(name = "schedule_id", unique = true)
    Long id;

    @Column(name = "wod_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    Date wodDate;

    @Column(name = "notes")
    String notes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    Organization organization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wod_id")
    Wod wod;

}
