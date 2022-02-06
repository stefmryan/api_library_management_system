package com.steffiecodes.lms.services;

import com.steffiecodes.lms.models.Catalog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatalogService {
    List<Catalog> getAllItems();
}
