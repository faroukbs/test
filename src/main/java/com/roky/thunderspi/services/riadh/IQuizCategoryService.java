package com.roky.thunderspi.services.riadh;

import com.roky.thunderspi.entities.QuizCategory;
import com.roky.thunderspi.entities.QuizCategory;

import java.util.Set;


public interface IQuizCategoryService  {
    public QuizCategory getQuizCategoryById(Long id);
    public Set<QuizCategory> findAll();
    public void deleteQuizCategory(Long id);
    public QuizCategory updateQuizCategory(QuizCategory q);
    public QuizCategory addQuizCategory(QuizCategory q);
}
