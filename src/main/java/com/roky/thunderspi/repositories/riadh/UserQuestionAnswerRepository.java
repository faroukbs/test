package com.roky.thunderspi.repositories.riadh;


import com.roky.thunderspi.entities.UserQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestionAnswerRepository extends JpaRepository<UserQuestionAnswer,Long> {
}
