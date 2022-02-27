package com.steffiecodes.lms.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private long barcode;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private String deweyDecimal;

    @Column(length = 1000)
    @NotBlank
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull
    private boolean available;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "LIBRARYACCOUNT_ID")
    private LibraryAccount libraryAccount;

    @NotNull
    @Max(value = 2, message = "Can only have 2 renewals")
    private Integer renew;

    public Catalog() {

    }

    public Catalog(long barcode, String title, String author, String deweyDecimal, String description, LocalDate dueDate, boolean available, LibraryAccount libraryAccount, Integer renew) {
        this.barcode = barcode;
        this.title = title;
        this.author = author;
        this.deweyDecimal = deweyDecimal;
        this.description = description;
        this.dueDate = dueDate;
        this.available = available;
        this.libraryAccount = libraryAccount;
        this.renew = renew;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LibraryAccount getLibraryAccount() {
        return libraryAccount;
    }

    public void setLibraryAccount(LibraryAccount libraryAccount) {
        this.libraryAccount = libraryAccount;
    }

    public Integer getRenew() {
        return renew;
    }

    public void setRenew(Integer renew) {
        this.renew = renew;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeweyDecimal() {
        return deweyDecimal;
    }

    public void setDeweyDecimal(String deweyDecimal) {
        this.deweyDecimal = deweyDecimal;
    }


    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", barcode=" + barcode +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", deweyDecimal='" + deweyDecimal + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", available=" + available +
                ", libraryAccount=" + libraryAccount +
                ", renew=" + renew +
                '}';
    }
}
