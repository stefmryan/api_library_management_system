package com.steffiecodes.lms.controllers;


import com.steffiecodes.lms.models.Catalog;
import com.steffiecodes.lms.models.LibraryAccount;
import com.steffiecodes.lms.services.LibraryAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class LibraryAccountController {
    Logger logger = LoggerFactory.getLogger(LibraryAccountController.class);

    @Autowired
    LibraryAccountService libraryAccountService;


    @GetMapping("/library-accounts")
    public List<LibraryAccount> getLibraryAccounts() {
        logger.info("Request for get All Library Accounts");
        return libraryAccountService.getLibraryAccounts();
    }

    @GetMapping("/library-accounts/account")
    public ResponseEntity<List<LibraryAccount>> getAccountByNumberOrName(@RequestParam String accountData) {
        logger.info("Request for Library Account " + accountData);
        return libraryAccountService.getAccountByNumberOrName(accountData);
    }

    @PostMapping("/register")
    public ResponseEntity<String> postLibraryAccount(@RequestBody LibraryAccount newLibraryAccount) {
        logger.info("Request for new Library Account");
        return libraryAccountService.addLibraryAccount(newLibraryAccount);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LibraryAccount> putLibraryAccount(@PathVariable long id, @RequestBody LibraryAccount libraryAccount){
        logger.info("Request made to update library account");
        return libraryAccountService.putLibraryAccount(id, libraryAccount);
    }

    @PutMapping("/checkout/{itemBarcode}")
    public ResponseEntity<Catalog> putCheckOut(@RequestBody LibraryAccount libraryAccount, @PathVariable long itemBarcode){
        logger.info("Request made for checkout");
        return libraryAccountService.putCheckOut(libraryAccount, itemBarcode);
    }

    @PatchMapping("/renew/{id}")
    public ResponseEntity<Catalog> patchCatalogItem(@PathVariable long id){
        logger.info("Request made for item renewal");
        return libraryAccountService.patchCatalogItem(id);
    }

    @PutMapping("/checkin")
    public ResponseEntity<List<Long>> putCheckInItem(@RequestBody List<Long> itemBarcodes){
        logger.info("Request made for items checkin");
        return libraryAccountService.putCheckInItem(itemBarcodes);
    }
}
