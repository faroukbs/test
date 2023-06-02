package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.UserQuestionAnswer;
import com.roky.thunderspi.repositories.riadh.UserQuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IUserQuestionAnswerServiceImp implements IUserQuestionAnswerService {
    private final UserQuestionAnswerRepository userQuestionAnswerRepository;
    @Override
    public UserQuestionAnswer addQuestionAnswer(UserQuestionAnswer q) {
        return userQuestionAnswerRepository.save(q);
    }

    @Override
    public UserQuestionAnswer updateQuestion(UserQuestionAnswer q) {
        return userQuestionAnswerRepository.save(q);
    }

    @Override
    public Set<UserQuestionAnswer> findAll() {
        return userQuestionAnswerRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public UserQuestionAnswer findById(Long id) {
        return userQuestionAnswerRepository.findById(id).orElse(null);
    }

    @Override
    public Set<UserQuestionAnswer> findAllbyQuestion(Long questionId) {
        return userQuestionAnswerRepository.findAll().stream().filter(q ->q.getQuestion().getId().equals(questionId)).collect(Collectors.toSet());
    }

    @Override
    public Set<UserQuestionAnswer> findAllbyQuiz(Long quizId) {
        return userQuestionAnswerRepository.findAll().stream().filter(q -> q.getQuestion().getQuiz().getId().equals(quizId)).collect(Collectors.toSet());
    }

    @Override
    public void delete(Long id)
    {
        userQuestionAnswerRepository.deleteById(id);
    }


}
