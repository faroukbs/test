package com.roky.thunderspi.repositories.riadh;


import com.roky.thunderspi.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
    @Query("select q from Quiz q where q.teacher = ?1")
    Set<Quiz> findQuizByTeacher(Long id);


}
