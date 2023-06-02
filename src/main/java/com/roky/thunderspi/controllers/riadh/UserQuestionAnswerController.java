package com.roky.thunderspi.controllers.riadh;

import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.entities.UserQuestionAnswer;
import com.roky.thunderspi.services.riadh.IUserQuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userQstAns")
@CrossOrigin("*")
public class UserQuestionAnswerController {

    private final IUserQuestionAnswerService userQuestionAnswerService;


    @GetMapping("/{id}")
    public UserQuestionAnswer getById(@PathVariable("id") Long id)
    {
        return userQuestionAnswerService.findById(id);
    }

    @GetMapping
    public Set<UserQuestionAnswer> getAll()
    {
        return userQuestionAnswerService.findAll();
    }

    @PostMapping
    public UserQuestionAnswer add(@RequestBody UserQuestionAnswer userQuestionAnswer)
    {
        return userQuestionAnswerService.addQuestionAnswer(userQuestionAnswer);
    }
    @PutMapping
    public UserQuestionAnswer update(@RequestBody UserQuestionAnswer userQuestionAnswer)
    {
        return userQuestionAnswerService.addQuestionAnswer(userQuestionAnswer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id)
    {
        userQuestionAnswerService.delete(id);
    }

    @GetMapping("/byQuestion/{id}")
    public Set<UserQuestionAnswer> getAllByQuestion(@PathVariable("id") Long id)
    {
        return userQuestionAnswerService.findAllbyQuestion(id);
    }
    @GetMapping("/byQuiz/{id}")
    public Set<UserQuestionAnswer> getAllByQuiz(@PathVariable("id") Long id)
    {
        return userQuestionAnswerService.findAllbyQuiz(id);
    }


}
