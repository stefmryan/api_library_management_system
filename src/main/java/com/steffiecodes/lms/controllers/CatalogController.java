package com.steffiecodes.lms.controllers;

import com.steffiecodes.lms.models.Catalog;
import com.steffiecodes.lms.services.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalog")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", maxAge = 3600)
public class CatalogController {

    Logger logger = LoggerFactory.getLogger(LibraryAccountController.class);

    @Autowired
    CatalogService catalogService;

    @GetMapping
    public List<Catalog> getAllItems() {
        logger.info("Request for get All Catalog Items");
        return catalogService.getAllItems();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Catalog>> getItemsByTitleOrAuthor(@RequestParam (required = false)String title,@RequestParam (required = false)String author ) {
        logger.info("Request for " + title);
        return catalogService.getItemsByTitleOrAuthor(title, author);
    }
}
