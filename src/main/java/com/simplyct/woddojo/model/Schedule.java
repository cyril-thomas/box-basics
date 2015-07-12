package com.simplyct.woddojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cyril on 7/11/15.
 */
@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "schedule_id", unique = true)
    Long id;

    @Column(name = "date")
    @NotEmpty(message = "Date to do the wod is required")
    @Temporal(TemporalType.DATE)
    Date date;

}
