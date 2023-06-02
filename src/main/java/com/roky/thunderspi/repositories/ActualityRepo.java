package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.Actuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualityRepo extends JpaRepository<Actuality,Long> {
}
