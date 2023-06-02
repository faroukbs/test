package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepo extends JpaRepository<Meeting,Long> {
}
