package com.steffiecodes.lms.repositories;

import com.steffiecodes.lms.models.LibraryAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryAccountRepository extends JpaRepository<LibraryAccount, Long> {
    boolean existsByLibraryAccountNumber(Integer libraryAccountNumber);
    boolean existsByIdNumber(String idNumber);

    LibraryAccount findByLibraryAccountNumber(Integer libraryAccountNumber);
    List<LibraryAccount> findByFirstNameAndLastName(String firstName, String lastName);
}
