package com.steffiecodes.lms.repositories;

import com.steffiecodes.lms.models.Holds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldsRepository extends JpaRepository<Holds, Long> {

}
