package edu.simplyct.boxbasics.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by cyril on 6/11/15.
 */
@Entity
@Table(name = "wod")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
