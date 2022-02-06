package com.steffiecodes.lms.serviceImpls;

import com.steffiecodes.lms.models.Catalog;
import com.steffiecodes.lms.repositories.CatalogRepository;
import com.steffiecodes.lms.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CatalogRepository catalogRepository;

    public List<Catalog> getAllItems() {
        return catalogRepository.findAll();
    }
}
