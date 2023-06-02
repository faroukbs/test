package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Question;
import com.roky.thunderspi.entities.UserQuestionAnswer;
import com.roky.thunderspi.repositories.riadh.QuestionAnswerRepository;
import com.roky.thunderspi.repositories.riadh.QuestionRepository;
import com.roky.thunderspi.repositories.riadh.UserQuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IQuestionServiceImp implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UserQuestionAnswerRepository userQuestionAnswerRepository;
    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public Set<Question> findAll() {
        return questionRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Question updateQuestion(Question q) {
        Question toUpdate= questionRepository.findById(q.getId()).orElse(null);
        if(toUpdate!= null)
        {
            return toUpdate;
        }else
            return null;
    }

    @Override
    public Question addQuestion(Question q) {
        return questionRepository.save(q);
    }

    @Override
    public Set<Question> getQuestionsByCorrectAnswers() {
        return questionRepository.findAll().stream()
                .sorted((q1,q2)->
                        getCorrectAnswerCount(q2)-getCorrectAnswerCount(q1)
                ).collect(Collectors.toCollection(LinkedHashSet::new));


    }

    public int getCorrectAnswerCount(Question q)
    {
        return userQuestionAnswerRepository.findAll().stream()
                .filter(uqa->uqa.getQuestion().getId().equals(q.getId()))
                .filter(uqa->uqa.getQuestionAnswer().isCorrect())
                .collect(Collectors.toSet()).size();
    }
}
