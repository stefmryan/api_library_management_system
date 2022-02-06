package com.steffiecodes.lms.repositories;

import com.steffiecodes.lms.models.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends JpaRepository<County, Long> {
}
