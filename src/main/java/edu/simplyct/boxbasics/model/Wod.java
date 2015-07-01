package edu.simplyct.boxbasics.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/11/15.
 */
@Entity
@Table(name = "wod")
@Data
@ToString
public class Wod {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "wod_id", unique = true)
    Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name is required")
    String name;

    @Column(name = "description")
    @NotEmpty(message = "Last Name is required")
    String description;

    @Column(name = "notes")
    String notes;
}
