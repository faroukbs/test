package com.roky.thunderspi.repositories.riadh;

import com.roky.thunderspi.entities.QuizCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCategoryRepository extends JpaRepository<QuizCategory,Long> {
}
