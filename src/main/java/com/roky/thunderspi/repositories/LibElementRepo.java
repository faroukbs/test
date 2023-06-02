package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.LibElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibElementRepo extends JpaRepository<LibElement,Long> {
}
