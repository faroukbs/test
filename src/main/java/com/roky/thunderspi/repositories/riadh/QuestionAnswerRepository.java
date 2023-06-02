package com.roky.thunderspi.repositories.riadh;


import com.roky.thunderspi.entities.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {
}
