package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.QuestionAnswer;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.entities.UserQuestionAnswer;

import java.util.Set;

public interface IUserQuestionAnswerService {
    public UserQuestionAnswer addQuestionAnswer(UserQuestionAnswer q);
    public UserQuestionAnswer updateQuestion(UserQuestionAnswer q);
    public Set<UserQuestionAnswer>  findAll();

    public UserQuestionAnswer findById(Long id);

    public Set<UserQuestionAnswer> findAllbyQuestion(Long questionId);
    public Set<UserQuestionAnswer> findAllbyQuiz(Long quizId);

    public void delete(Long id);

}
