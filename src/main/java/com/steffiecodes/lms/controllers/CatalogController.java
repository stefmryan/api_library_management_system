package com.steffiecodes.lms.controllers;

import com.steffiecodes.lms.models.Catalog;
import com.steffiecodes.lms.services.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {

    Logger logger = LoggerFactory.getLogger(LibraryAccountController.class);

    @Autowired
    CatalogService catalogService;

    @GetMapping("/catalog")
    public List<Catalog> getAllItems() {
        logger.info("Request for get All Catalog Items");
        return catalogService.getAllItems();
    }
}
