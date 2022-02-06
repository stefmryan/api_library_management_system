package com.steffiecodes.lms.models;

import javax.persistence.*;

@Entity
@Table(name="ACCOUNTTYPES")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "label", nullable = false)
    private String label;

    public AccountType() {

    }

    public AccountType(String label) {
        this.label = label;
    }

    public AccountType(long id, String label) {
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
        return "AccountType{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
