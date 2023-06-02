package com.roky.thunderspi.controllers.riadh;


import com.roky.thunderspi.entities.Question;
import com.roky.thunderspi.services.riadh.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/qst")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionController {
    private final IQuestionService questionService;

    @GetMapping
    public Set<Question> getAll()
    {
        return questionService.findAll();
    }
    @GetMapping("/{id}")
    public Question getById(@PathVariable Long id)
    {
        return questionService.getQuestionById(id);
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question q)
    {
        return questionService.addQuestion(q);
    }

    @PutMapping
    public Question updateQuestion(@RequestBody Question q)
    {
        return questionService.updateQuestion(q);
    }
    @DeleteMapping("/del{id}")
    public void deleteQuestion(@PathVariable Long id)
    {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/correct")
    public Set<Question> getQuestionsByCorrectAnswers()
    {
        return questionService.getQuestionsByCorrectAnswers();
    }
}
