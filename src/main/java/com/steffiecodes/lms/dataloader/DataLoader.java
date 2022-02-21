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


@Service
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LibraryAccountRepository libraryAccountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

        for (String label : ACCOUNT_TYPE_LABELS) {
            accountType = accountTypeRepository.save(new AccountType(label));
        }
    }

    private void addItemsToCatalog() {
        List<Catalog> initialTestCatalogLoader = new ArrayList<>();

        Catalog book1 = new Catalog(98765432, "The Fault In Our Stars", "John Green","YA FIC GRE", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc.", null, true, null, 0);
        Catalog book2 = new Catalog(1234567, "Harry Potter and The Sorceror's Stone", "J.K. Rowling", "J FIC ROW","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc.",null, true, null, 0);
        Catalog book3 = new Catalog(456783456, "The Cat In The Hat", "Dr. Seuss","JE SEU" ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc." ,null, true, null, 0);
        Catalog book4 = new Catalog(987657890, "The Shining", "Stephen King", "FIC KIN","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc." ,null, true, null, 0);
        Catalog book5 = new Catalog(234576543, "Flowers for Algernon", "Daniel Keyes","FIC KEY" ,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc." ,null, true, null, 0);
        Catalog book6 = new Catalog(78654777, "Lord of the Rings", "J.R.R. Tolkien","FIC TOL","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc." ,null, true, null, 0);
        Catalog book7 = new Catalog(77754321, "SlaughterHouse Five", "Kurt Vonnegut, Jr","FIC VON","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc." ,null, true, null, 0);
        Catalog book8 = new Catalog(87612543, "Lord of the Flies", "William Golding", "YA FIC GOL", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc.", null, true, null, 0);
        Catalog book9 = new Catalog(34598876, "Pet Semetary", "Stephen King", "FIC KIN", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Pharetra sit amet aliquam id diam maecenas ultricies. Sit amet tellus cras adipiscing enim eu. Sem fringilla ut morbi tincidunt augue interdum velit. Euismod nisi porta lorem mollis aliquam ut porttitor. Adipiscing bibendum est ultricies integer quis auctor elit sed. Praesent elementum facilisis leo vel. Ac odio tempor orci dapibus ultrices in. Mi quis hendrerit dolor magna. Pharetra diam sit amet nisl suscipit adipiscing. Integer feugiat scelerisque varius morbi enim. Nec ullamcorper sit amet risus nullam eget felis eget nunc.", null, true, null, 0);
        initialTestCatalogLoader.addAll(Arrays.asList(book1, book2, book3, book4, book5, book6, book7, book8, book9));

        catalogRepository.saveAll(initialTestCatalogLoader);
    }

    private void addLibraryAccounts() {
        LocalDate date1 = LocalDate.of(1997, 02, 9);
        LocalDate date2 = LocalDate.of(1967, 06, 28);
        LocalDate date3 = LocalDate.of(2014, 10, 31);

        //Object Arrays
        List<AccountType> accountTypeObjectList = new ArrayList<>();

        //Load Object from Labels

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
                "Red", accountTypeObjectList.get(1), null);

        LibraryAccount betty2 = new LibraryAccount(77755, "BabyBetty@fakeEmail.com",
                "Betty", "Barnes",
                "321-345-765", date1, "555-555-5577", "777 Lucky Ave",
                "Apt 2B", "London", "OR", "99876", "Red", accountTypeObjectList.get(0), null);

        LibraryAccount james = new LibraryAccount(54321, "jamesPatterson@fakeEmail.com",
                "James", "Patterson",
                "321-546-987", date2, "555-555-0987", "3245 Oak Ave.",
                "Apt 2B", "MainVille", "OR", "99876",
                "Brown", accountTypeObjectList.get(1), null);

        LibraryAccount izzy = new LibraryAccount(98769, "izzySmith@fakeEmail.com",
                "Isabella", "Smith",
                "987-678-435", date3, "555-555-6539", "975 Woodlawn Blvd.",
                "", "MainVille", "OR", "99876",
               "Green", accountTypeObjectList.get(2), null);

        initialTestLibraryAccountLoader.add(betty);
        initialTestLibraryAccountLoader.add(betty2);
        initialTestLibraryAccountLoader.add(james);
        initialTestLibraryAccountLoader.add(izzy);

        libraryAccountRepository.saveAll(initialTestLibraryAccountLoader);
    }


}
