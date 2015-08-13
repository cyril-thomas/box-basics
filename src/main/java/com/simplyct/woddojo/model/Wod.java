package com.simplyct.woddojo.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/11/15.
 */
@Entity
@Table(name = "wod")
@Data
public class Wod {

    @Id
    @SequenceGenerator(name = "seq_wod", sequenceName = "seq_wod", schema = "public", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_wod")
    @Column(name = "wod_id", unique = true)
    Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name is required")
    String name;

    @Column(name = "description")
    @NotEmpty(message = "Description is required")
    String description;

    @Column(name = "notes")
    String notes;
}
