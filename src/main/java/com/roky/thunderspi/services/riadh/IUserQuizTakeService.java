package com.roky.thunderspi.services.riadh;

import com.roky.thunderspi.entities.UserQuizTake;

import java.util.Set;

public interface IUserQuizTakeService {
    public UserQuizTake addQuizTake(UserQuizTake take);
    public UserQuizTake updateQuizTake(UserQuizTake take);

    public Set<UserQuizTake> findAll();
    public Set<UserQuizTake> findByQuiz(Long quizId);
    public void deleteQuizTake(Long id);
    public UserQuizTake findById(Long id);

    public Set<UserQuizTake> getQuizTakesByUser(Long id);
}
