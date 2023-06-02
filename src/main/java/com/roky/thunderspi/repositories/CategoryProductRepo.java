package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryProductRepo extends JpaRepository<CategoryProduct,Long> {
    Optional<CategoryProduct> findCategoryById(Long id);
}
