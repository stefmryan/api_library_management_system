package com.steffiecodes.lms.serviceImpls;

import com.steffiecodes.lms.models.Catalog;
import com.steffiecodes.lms.repositories.CatalogRepository;
import com.steffiecodes.lms.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

    public List<Catalog> getAllItems() {
        return catalogRepository.findAll();
    }


    public ResponseEntity<List<Catalog>> getItemsByTitleOrAuthor(String title, String author) {
        if (title != null) {
            return ResponseEntity.ok(catalogRepository.findByTitle(title));
        }
        if (author != null) {
            return ResponseEntity.ok(catalogRepository.findByAuthor(author));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
