package com.steffiecodes.lms.serviceImpls;

import com.steffiecodes.lms.models.Catalog;
import com.steffiecodes.lms.models.LibraryAccount;
import com.steffiecodes.lms.repositories.CatalogRepository;
import com.steffiecodes.lms.repositories.HoldsRepository;
import com.steffiecodes.lms.repositories.LibraryAccountRepository;
import com.steffiecodes.lms.services.LibraryAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryAccountServiceImpl implements LibraryAccountService {
    @Autowired
    LibraryAccountRepository libraryAccountRepository;

    @Autowired
    CatalogRepository catalogRepository;

    @Autowired
    HoldsRepository holdsRepository;

    public final boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }

        return containsDigit;
    }

    public List<LibraryAccount> getLibraryAccounts() {
        return libraryAccountRepository.findAll();
    }

    public ResponseEntity<List<LibraryAccount>> getAccountByNumberOrName(String libraryAccountData) {
        boolean digitExists = containsDigit(libraryAccountData);
        List<LibraryAccount> requestedAccount = new ArrayList<LibraryAccount>();

        if (digitExists) {
            int libraryAccountNumber = Integer.parseInt(libraryAccountData);
            LibraryAccount account = libraryAccountRepository.findByLibraryAccountNumber(libraryAccountNumber);
            if (account == null) {
                return ResponseEntity.notFound().build();
            }
            requestedAccount.add(account);
            return ResponseEntity.ok(requestedAccount);

        } else {
            String firstName = libraryAccountData.substring(0, libraryAccountData.indexOf(' '));
            String lastName = libraryAccountData.substring(libraryAccountData.indexOf(' ') + 1);
            List<LibraryAccount> listOfAccts = libraryAccountRepository.findByFirstNameAndLastName(firstName, lastName);
            if (listOfAccts.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(listOfAccts);
        }
    }

    public ResponseEntity<String> addLibraryAccount(LibraryAccount newLibraryAccount) {

        try {
            Integer libraryAccountNumber = newLibraryAccount.getLibraryAccountNumber();
            boolean newLibraryAccountNumber =
                    libraryAccountRepository.existsByLibraryAccountNumber(libraryAccountNumber);
            //if library account number does not exist in the database, add new library account
            if (!newLibraryAccountNumber) {
                libraryAccountRepository.save(newLibraryAccount);
                return new ResponseEntity<>("Library Account created", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Library Account Number is not valid",
                        HttpStatus.CONFLICT);
            }
        } catch (DataAccessException err) {
            throw new RuntimeException(err);
        }


    }

    public ResponseEntity<Catalog> putCheckOut(LibraryAccount libraryAccount, long itemBarcode) {

        try {
            boolean bookDoesExist = catalogRepository.existsByBarcode(itemBarcode);
            int libraryAccountNumber = libraryAccount.getLibraryAccountNumber();
            boolean libraryAccountDoesExist = libraryAccountRepository.existsByLibraryAccountNumber(libraryAccountNumber);
            LocalDate dueDate = LocalDate.now().plusDays(14);
            Catalog item1 = catalogRepository.findByBarcode(itemBarcode);
            LibraryAccount patron = libraryAccountRepository.findByLibraryAccountNumber(libraryAccountNumber);
            List<Catalog> items = patron.getBooks();

            if (!libraryAccountDoesExist || !bookDoesExist) {
                return ResponseEntity.notFound().build();
            }

            for (Catalog eachItem : items) {
                if (eachItem.getBarcode() == itemBarcode) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }

            if (!item1.isAvailable()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }


            //if book record exists and is available and library account exists, add book to list of books
            //associated to an account.  Change the due Date of an item and change the availability of
            //of the book.

            item1.setAvailable(false);
            item1.setDueDate(dueDate);
            item1.setLibraryAccount(patron);
            catalogRepository.save(item1);

            Catalog updatedItem1 = catalogRepository.findById(item1.getId()).get();
            List<Catalog> bookList = new ArrayList<>();
            bookList.add(updatedItem1);

            if (!items.isEmpty()) {
                bookList.addAll(items);
            }
            patron.setBooks(bookList);
            libraryAccountRepository.save(patron);

            return ResponseEntity.ok().body(updatedItem1);
        } catch (NullPointerException ex) {
            ex.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<LibraryAccount> putLibraryAccount(long id, LibraryAccount libraryAccount) {
        //check that account exists
        boolean accountExists =
                libraryAccountRepository.existsById(id);
        if (!accountExists) {
            return ResponseEntity.notFound().build();
        }

        List<Catalog> itemList = libraryAccount.getBooks();
        Integer libraryAccountNumber = libraryAccount.getLibraryAccountNumber();
        LibraryAccount account = libraryAccountRepository.findByLibraryAccountNumber(libraryAccountNumber);
        List<Catalog> hasBookList = account.getBooks();

        if (itemList.isEmpty() && !hasBookList.isEmpty()) {
            libraryAccount.setBooks(hasBookList);
        }
        return libraryAccountRepository.findById(id)
                .map(libraryAccount1 -> {
                    libraryAccount1.setIdNumber(libraryAccount.getIdNumber());
                    libraryAccount1.setLibraryAccountNumber(libraryAccount.getLibraryAccountNumber());
                    //this set books may give me trouble.  Verify that books do not change duedate
                    libraryAccount1.setBooks(libraryAccount.getBooks());
                    libraryAccount1.setZipCode(libraryAccount.getZipCode());
                    libraryAccount1.setTelephone(libraryAccount.getTelephone());
                    libraryAccount1.setAccountType(libraryAccount.getAccountType());
                    libraryAccount1.setState(libraryAccount.getState());
                    libraryAccount1.setStreet(libraryAccount.getStreet());
                    libraryAccount1.setStreet2(libraryAccount.getStreet2());
                    libraryAccount1.setLastName(libraryAccount.getLastName());
                    libraryAccount1.setFirstName(libraryAccount.getFirstName());
                    libraryAccount1.setEmail(libraryAccount.getEmail());
                    libraryAccount1.setCounty(libraryAccount.getCounty());
                    libraryAccount1.setBirthdate(libraryAccount.getBirthdate());
                    libraryAccountRepository.save(libraryAccount1);
                    return ResponseEntity.ok(libraryAccount1);
                })
                .orElseGet(() -> {
                    libraryAccount.setId(id);
                    libraryAccountRepository.save(libraryAccount);
                    return ResponseEntity.ok(libraryAccount);
                });
    }

    public ResponseEntity<Catalog> patchCatalogItem(long id) {

        boolean existsById = catalogRepository.existsById(id);

        //check if item exists
        if (!existsById) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Catalog item = catalogRepository.findById(id).get();
        Integer renewal = item.getRenew();
        LibraryAccount libraryAccount = item.getLibraryAccount();

        //check if reached renewal limit or that item has a library account attached.
        if (renewal == 2 || libraryAccount == null) {
            return ResponseEntity.badRequest().build();
        }

        //update catalog item & library account list of books.
        LocalDate renewedDate = LocalDate.now().plusDays(14);
        item.setDueDate(renewedDate);
        item.setRenew(renewal + 1);
        catalogRepository.save(item);
        libraryAccountRepository.save(libraryAccount);

        return ResponseEntity.ok(item);
    }

    public ResponseEntity<List<Long>> putCheckInItem(List<Long> itemBarcodes) {
        List<Long> barcodeDoesNotExist = new ArrayList<Long>();
        if (itemBarcodes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        for (long barcode : itemBarcodes) {
            boolean itemExists = catalogRepository.existsByBarcode(barcode);
            if (!itemExists) {
                barcodeDoesNotExist.add(barcode);
            } else {
                Catalog itemToCheckIn = catalogRepository.findByBarcode(barcode);
                LibraryAccount libraryAccount = itemToCheckIn.getLibraryAccount();
                if (libraryAccount == null) {
                    return ResponseEntity.status(HttpStatus.OK).body(barcodeDoesNotExist);
                }
                List<Catalog> itemList = libraryAccount.getBooks();
                itemList.removeIf(item -> item.getBarcode() == barcode);


                itemToCheckIn.setLibraryAccount(null);
                itemToCheckIn.setDueDate(null);
                itemToCheckIn.setRenew(0);
                itemToCheckIn.setAvailable(true);

                catalogRepository.save(itemToCheckIn);
                libraryAccountRepository.save(libraryAccount);

            }
        }
        if (!barcodeDoesNotExist.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(barcodeDoesNotExist);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(barcodeDoesNotExist);
    }
}
