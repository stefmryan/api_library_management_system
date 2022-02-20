package com.steffiecodes.lms.repositories;

import com.steffiecodes.lms.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    boolean existsByBarcode(long barcode);
    Catalog findByBarcode(long barcode);
    List<Catalog> findByTitle(String title);
    List<Catalog> findByAuthor(String Author);
}
