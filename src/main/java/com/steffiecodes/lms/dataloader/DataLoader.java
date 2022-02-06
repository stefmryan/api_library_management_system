package com.steffiecodes.lms.dataloader;

import com.steffiecodes.lms.models.*;
import com.steffiecodes.lms.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.steffiecodes.lms.constants.StringConstants.ACCOUNT_TYPE_LABELS;
import static com.steffiecodes.lms.constants.StringConstants.COUNTY_LABELS;

@Service
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LibraryAccountRepository libraryAccountRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private County county;
    private AccountType accountType;
    private Catalog catalog;


    @Override
    public void run(String... args) throws Exception {
        loadUsers();
        loadSelectTables();
        addLibraryAccounts();
        addItemsToCatalog();
    }

    public void loadUsers() {
        AppUser admin = new AppUser("admin@library.org", passwordEncoder.encode("Password123"), "ADMIN", "Ad", "Min");
        AppUser manager = new AppUser("manager@library.org", passwordEncoder.encode("Password123"), "MANAGER", "Mana", "Ger");
        AppUser employee = new AppUser("employee@library.org", passwordEncoder.encode("Password123"), "EMPLOYEE", "Em", "Ploy");

        List<AppUser> users = Arrays.asList(admin, manager, employee);
        appUserRepository.saveAll(users);
    }

    private void loadSelectTables() {
        for (String label : COUNTY_LABELS) {
            county = countyRepository.save(new County(label));
        }
        for (String label : ACCOUNT_TYPE_LABELS) {
            accountType = accountTypeRepository.save(new AccountType(label));
        }
    }

    private void addItemsToCatalog() {
        List<Catalog> initialTestCatalogLoader = new ArrayList<>();

        Catalog book1 = new Catalog(98765432, "The Fault In Our Stars", "John Green", null, true, null, 0);
        Catalog book2 = new Catalog(1234567, "Harry Potter and The Sorceror's Stone", "J.K. Rowling", null, true, null, 0);
        Catalog book3 = new Catalog(456783456, "The Cat In The Hat", "Dr. Seuss", null, true, null, 0);
        Catalog book4 = new Catalog(987657890, "The Shining", "Stephen King", null, true, null, 0);
        Catalog book5 = new Catalog(234576543, "Flowers for Algernon", "Daniel Keyes", null, true, null, 0);
        Catalog book6 = new Catalog(78654777, "Lord of the Rings", "J.R.R. Tolkien", null, true, null, 0);

        initialTestCatalogLoader.add(book1);
        initialTestCatalogLoader.add(book2);
        initialTestCatalogLoader.add(book3);
        initialTestCatalogLoader.add(book4);
        initialTestCatalogLoader.add(book5);
        initialTestCatalogLoader.add(book6);

        catalogRepository.saveAll(initialTestCatalogLoader);
    }

    private void addLibraryAccounts() {
        LocalDate date1 = LocalDate.of(1997, 02, 9);
        LocalDate date2 = LocalDate.of(1967, 06, 28);
        LocalDate date3 = LocalDate.of(2014, 10, 31);

//        List<Catalog> initialTestCatalogLoader = new ArrayList<>();
//
//        Catalog book1 = new Catalog(98765432, "The Fault In Our Stars", "John Green", null, true, null);
//        Catalog book2 = new Catalog(1234567, "Harry Potter and The Sorceror's Stone", "J.K. Rowling", null, true, null);
//
//        initialTestCatalogLoader.add(book1);
//        initialTestCatalogLoader.add(book2);

        //Object Arrays
        List<County> countyObjectList = new ArrayList<>();
        List<AccountType> accountTypeObjectList = new ArrayList<>();

        //Load Object from Labels

        for (Long i = 0L; i < COUNTY_LABELS.size(); i++) {
            countyObjectList.add(new County(i + 1,
                    COUNTY_LABELS.get(Math.toIntExact(i))));
        }

        for (Long i = 0L; i < ACCOUNT_TYPE_LABELS.size(); i++) {
            accountTypeObjectList.add(new AccountType(i + 1,
                    ACCOUNT_TYPE_LABELS.get(Math.toIntExact(i))));
        }

        //List containing all libraryAccount objects
        List<LibraryAccount> initialTestLibraryAccountLoader = new ArrayList<>();


        LibraryAccount betty = new LibraryAccount(12345, "bookwormBetty@fakeEmail.com",
                "Betty", "Barnes",
                "123-345-567", date1, "555-555-6789", "123 Main St",
                "", "MainVille", "OR", "99876",
                countyObjectList.get(1), accountTypeObjectList.get(1), null);

        LibraryAccount james = new LibraryAccount(54321, "jamesPatterson@fakeEmail.com",
                "James", "Patterson",
                "321-546-987", date2, "555-555-0987", "3245 Oak Ave.",
                "Apt 2B", "MainVille", "OR", "99876",
                countyObjectList.get(2), accountTypeObjectList.get(1), null);

        LibraryAccount izzy = new LibraryAccount(98769, "izzySmith@fakeEmail.com",
                "Isabella", "Smith",
                "987-678-435", date3, "555-555-6539", "975 Woodlawn Blvd.",
                "", "MainVille", "OR", "99876",
                countyObjectList.get(2), accountTypeObjectList.get(2), null);

        initialTestLibraryAccountLoader.add(betty);
        initialTestLibraryAccountLoader.add(james);
        initialTestLibraryAccountLoader.add(izzy);

        libraryAccountRepository.saveAll(initialTestLibraryAccountLoader);
    }


}
