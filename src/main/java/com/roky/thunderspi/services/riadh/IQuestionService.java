package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Question;

import java.util.Set;

public interface IQuestionService {

    public Question getQuestionById(Long id);
    public Set<Question> findAll();
    public void deleteQuestion(Long id);
    public Question updateQuestion(Question q);
    public Question addQuestion(Question q);

    public Set<Question> getQuestionsByCorrectAnswers();
}
