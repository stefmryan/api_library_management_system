package com.steffiecodes.lms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Holds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate expirationDate;

    private Integer total;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CATALOG_ID")
    private Catalog catalog;

    @ManyToMany(mappedBy = "accountHolds")
    @JsonIgnore
    private List<LibraryAccount> libraryAccountsHoldsList;

    public Holds() {
    }

    public Holds(LocalDate expirationDate, Integer total, Catalog catalog, List<LibraryAccount> libraryAccountsHoldsList) {
        this.expirationDate = expirationDate;
        this.total = total;
        this.catalog = catalog;
        this.libraryAccountsHoldsList = libraryAccountsHoldsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<LibraryAccount> getLibraryAccountsHoldsList() {
        return libraryAccountsHoldsList;
    }

    public void setLibraryAccountsHoldsList(List<LibraryAccount> libraryAccountsHoldsList) {
        this.libraryAccountsHoldsList = libraryAccountsHoldsList;
    }

    @Override
    public String toString() {
        return "Holds{" +
                "id=" + id +
                ", expirationDate=" + expirationDate +
                ", total=" + total +
                ", catalog=" + catalog +
                ", libraryAccountsHoldsList=" + libraryAccountsHoldsList +
                '}';
    }
}
