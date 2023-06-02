package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.LibCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibCategoryrepo extends JpaRepository<LibCategory,Long> {
}
