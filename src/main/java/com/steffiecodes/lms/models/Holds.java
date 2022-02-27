package com.steffiecodes.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Holds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    private Catalog catalog;

    @ElementCollection
    private Set<Integer> libraryAccountNumber;

    private LocalDate expirationDate;

    private Integer total;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "LIBRARYACCOUNT_ID")
    private LibraryAccount libraryAccount;

    public Holds() {
    }

    public Holds(Catalog catalog, Set<Integer> libraryAccountNumber, LocalDate expirationDate, Integer total, LibraryAccount libraryAccount) {
        this.catalog = catalog;
        this.libraryAccountNumber = libraryAccountNumber;
        this.expirationDate = expirationDate;
        this.total = total;
        this.libraryAccount = libraryAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Set<Integer> getLibraryAccountNumber() {
        return libraryAccountNumber;
    }

    public void setLibraryAccountNumber(Set<Integer> libraryAccountNumber) {
        this.libraryAccountNumber = libraryAccountNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public LibraryAccount getLibraryAccount() {
        return libraryAccount;
    }

    public void setLibraryAccount(LibraryAccount libraryAccount) {
        this.libraryAccount = libraryAccount;
    }

    @Override
    public String toString() {
        return "Holds{" +
                "id=" + id +
                ", catalog=" + catalog +
                ", libraryAccountNumber=" + libraryAccountNumber +
                ", expirationDate=" + expirationDate +
                ", total=" + total +
                ", libraryAccount=" + libraryAccount +
                '}';
    }
}
