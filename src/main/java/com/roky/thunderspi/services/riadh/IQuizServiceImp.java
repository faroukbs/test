package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.Quiz;
import com.roky.thunderspi.repositories.CourseRepo;
import com.roky.thunderspi.repositories.riadh.QuizRepository;
import com.roky.thunderspi.repositories.riadh.UserQuizTakeRepository;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IQuizServiceImp implements IQuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private UserQuizTakeRepository userQuizTakeRepository;
    @Autowired
    private CourseRepo CourseRe;

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Quiz> getAllQuiz() {
        return quizRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Quiz addQuiz(Quiz q) {
        Course c = CourseRe.findById(q.getCourse().getIdCourse()).orElse(null);
        Assert.notNull(c,"Course not found");
        q.setCourse(c);
        return quizRepository.save(q);
    }

    @Override
    public Quiz editQuiz(Quiz q) {
        return quizRepository.save(q);
    }

    @Override
    public Set<Quiz> getQuizByTeacherId(Long id) {
        return quizRepository.findQuizByTeacher(id);
    }

    public Set<Quiz> getQuizByCourseId(Long id) {
        return quizRepository.findAll().stream().filter(q-> Objects.equals(q.getCourse().getIdCourse(), id)).collect(Collectors.toSet());
    }

    @Override
    public Set<Quiz> getQuizByNumberOfTakes(int takes) {

        return quizRepository.findAll().stream().filter(q->getNumberOfTakes(q)>takes).collect(Collectors.toSet());
    }

    public int getNumberOfTakes(Quiz z)
    {
        return userQuizTakeRepository.findAll().stream().filter(qt->qt.getQuiz().getId() == z.getId()).collect(Collectors.toSet()).size();
    }

    public Map<String, Number> getQuizStats(Long id) {
        Quiz q = quizRepository.findById(id).orElse(null);
        Assert.notNull(q,"Quiz not found");
        Map<String, Number> map = Map.of(
                "quiz", q.getId(),
                "takes", getNumberOfTakes(q),
                "average", getAverage(q),
                "max", getMax(q),
                "min", getMin(q)
        );
        return map;
    }

    public float getAverage(Quiz q ){
        if(getNumberOfTakes(q)>0)
        return userQuizTakeRepository.findAll().stream().filter(qt->qt.getQuiz().getId() == q.getId()).collect(Collectors.toSet()).stream().mapToInt(qt->(int)qt.getMark()).sum()/getNumberOfTakes(q);
        else return 0;
    }

    public float getMax(Quiz q)
    {
        return userQuizTakeRepository.findAll().stream().filter(qt->qt.getQuiz().getId() == q.getId()).collect(Collectors.toSet()).stream().mapToInt(qt->(int)qt.getMark()).max().orElse(0);

    }

    public float getMin(Quiz q)
    {
        return userQuizTakeRepository.findAll().stream().filter(qt->qt.getQuiz().getId() == q.getId()).collect(Collectors.toSet()).stream().mapToInt(qt->(int)qt.getMark()).min().orElse(0);
    }
}