package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.QuestionAnswer;
import com.roky.thunderspi.repositories.riadh.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IQuestionAnswerServiceImp implements IQuestionAnswerService {
    private final QuestionAnswerRepository questionAnswerRepository;
    @Override
    public QuestionAnswer findbyId(Long id) {
        return questionAnswerRepository.findById(id).get();
    }

    @Override
    public QuestionAnswer addQuestionAnswer(QuestionAnswer q) {
        return questionAnswerRepository.save(q);
    }

    @Override
    @Transactional
    public QuestionAnswer updateQuestionAnswer(QuestionAnswer q) {
        QuestionAnswer questionAnswer = questionAnswerRepository.findById(q.getId()).orElse(null);
        if(questionAnswer != null)
            return questionAnswer;
        return null;
    }

    @Override
    public Set<QuestionAnswer> findAll() {
        return questionAnswerRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteQuestionAnswer(Long id) {
        questionAnswerRepository.deleteById(id);
    }
}
