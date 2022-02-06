package com.steffiecodes.lms.repositories;

import com.steffiecodes.lms.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    boolean existsByBarcode(long barcode);
    Catalog findByBarcode(long barcode);
}
