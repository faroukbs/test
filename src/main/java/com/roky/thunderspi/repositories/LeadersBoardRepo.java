package com.roky.thunderspi.repositories;


import com.roky.thunderspi.entities.Leaderboards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadersBoardRepo  extends JpaRepository<Leaderboards,Long> {
}
