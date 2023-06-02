package com.roky.thunderspi.services.riadh;

import com.roky.thunderspi.entities.Quiz;

import java.util.Map;
import java.util.Set;

public interface IQuizService {
    public Quiz getQuizById(Long id);
    public Set<Quiz> getAllQuiz();
    public void deleteQuiz(Long id);
    public Quiz addQuiz(Quiz q);

    public Quiz editQuiz(Quiz q);

    public Set<Quiz> getQuizByTeacherId(Long id);
    public Set<Quiz> getQuizByNumberOfTakes(int takes);
    public Set<Quiz> getQuizByCourseId(Long id);
    public Map<String, Number> getQuizStats(Long id);

}
