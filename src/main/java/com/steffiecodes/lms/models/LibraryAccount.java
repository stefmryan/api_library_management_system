package com.steffiecodes.lms.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class LibraryAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer libraryAccountNumber;

    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String idNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @NotBlank
    private String telephone;

    @NotBlank
    private String street;

    private String street2;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String zipCode;

    @NotBlank

    private String county;

    @ManyToOne(cascade = CascadeType.MERGE)
    private AccountType accountType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Catalog> books;

    @ManyToMany
    @JoinTable(
            name = "accountHolds_libraryAccountsHoldsList",
            joinColumns = @JoinColumn(name = "libraryaccount_id"),
            inverseJoinColumns = @JoinColumn(name = "holds_id"))
    private List<Holds> accountHolds;

    public LibraryAccount() {

    }

    public LibraryAccount(Integer libraryAccountNumber, String email, String firstName, String lastName, String idNumber, LocalDate birthdate, String telephone, String street, String street2, String city, String state, String zipCode, String county, AccountType accountType, List<Catalog> books, List<Holds> accountHolds) {
        this.libraryAccountNumber = libraryAccountNumber;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.county = county;
        this.accountType = accountType;
        this.books = books;
        this.accountHolds = accountHolds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getLibraryAccountNumber() {
        return libraryAccountNumber;
    }

    public void setLibraryAccountNumber(Integer libraryAccountNumber) {
        this.libraryAccountNumber = libraryAccountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public List<Catalog> getBooks() {
        return books;
    }

    public void setBooks(List<Catalog> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "LibraryAccount{" +
                "id=" + id +
                ", libraryAccountNumber=" + libraryAccountNumber +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", birthdate=" + birthdate +
                ", telephone='" + telephone + '\'' +
                ", street='" + street + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", county='" + county + '\'' +
                ", accountType=" + accountType +
                ", books=" + books +
                '}';
    }
}
