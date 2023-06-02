package com.roky.thunderspi.repositories;


import com.roky.thunderspi.entities.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSubjectRepo  extends JpaRepository<CourseSubject,Long> {
}
