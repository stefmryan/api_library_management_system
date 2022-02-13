package com.steffiecodes.lms.services;

import com.steffiecodes.lms.models.Catalog;
import com.steffiecodes.lms.models.LibraryAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryAccountService {
    List<LibraryAccount> getLibraryAccounts();

    ResponseEntity<String> addLibraryAccount(LibraryAccount newLibraryAccount);

    LibraryAccount getAccountByNumberOrName(String libraryAccountData);

    ResponseEntity<Catalog> putCheckOut(LibraryAccount libraryAccount, long itemBarcode);

    ResponseEntity<LibraryAccount> putLibraryAccount(long id, LibraryAccount libraryAccount);

    ResponseEntity<Catalog> patchCatalogItem(long id);

    ResponseEntity<List<Long>> putCheckInItem(List<Long> itemBarcodes);
}
