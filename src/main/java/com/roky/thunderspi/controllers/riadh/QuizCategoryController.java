package com.roky.thunderspi.controllers.riadh;

import com.roky.thunderspi.entities.QuizCategory;
import com.roky.thunderspi.repositories.riadh.QuizCategoryRepository;
import com.roky.thunderspi.services.riadh.IQuizCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/quizCateg")
@CrossOrigin("*")
public class QuizCategoryController {
    private final IQuizCategoryService quizCategoryService;

    @GetMapping
    public Set<QuizCategory> getAll()
    {
        return quizCategoryService.findAll();
    }

    @PostMapping
    public QuizCategory add(@RequestBody QuizCategory q)
    {

        System.out.println("Added");
        return quizCategoryService.addQuizCategory(q);
    }

    @PutMapping
    public QuizCategory update(@RequestBody QuizCategory q)
    {
        return quizCategoryService.updateQuizCategory(q);
    }

    @GetMapping("/{id}")
    public QuizCategory getById(@PathVariable("id")Long id)
    {
        return quizCategoryService.getQuizCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id)
    {
        quizCategoryService.deleteQuizCategory(id);
    }


}
