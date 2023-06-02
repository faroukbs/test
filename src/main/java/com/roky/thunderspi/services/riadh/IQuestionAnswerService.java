package com.roky.thunderspi.services.riadh;

import com.roky.thunderspi.entities.QuestionAnswer;

import java.util.Set;

public interface IQuestionAnswerService {
    public QuestionAnswer findbyId(Long id);
    public QuestionAnswer addQuestionAnswer(QuestionAnswer q);
    public QuestionAnswer updateQuestionAnswer(QuestionAnswer q);
    public Set<QuestionAnswer> findAll();
    public void deleteQuestionAnswer(Long id);
}
