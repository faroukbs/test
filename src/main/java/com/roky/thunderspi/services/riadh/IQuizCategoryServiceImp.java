package com.roky.thunderspi.services.riadh;

import com.roky.thunderspi.entities.QuizCategory;
import com.roky.thunderspi.repositories.riadh.QuizCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IQuizCategoryServiceImp implements IQuizCategoryService{

    //TODO to implement
    private final QuizCategoryRepository quizCategoryRepository;
    @Override
    public QuizCategory getQuizCategoryById(Long id) {
        return quizCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public Set<QuizCategory> findAll() {
        return quizCategoryRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteQuizCategory(Long id) {
        quizCategoryRepository.deleteById(id);
    }

    @Override
    public QuizCategory updateQuizCategory(QuizCategory q) {
        return quizCategoryRepository.save(q);
    }

    @Override
    public QuizCategory addQuizCategory(QuizCategory q) {
        return quizCategoryRepository.save(q);
    }
}
