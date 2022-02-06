package com.steffiecodes.lms.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String label;

    public County(){}

    public County(String label) {
        this.label = label;
    }

    public County(long id, String label) {
        this.id = id;
        this.label = label;
    }

    public long getId(long l) {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "County{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
