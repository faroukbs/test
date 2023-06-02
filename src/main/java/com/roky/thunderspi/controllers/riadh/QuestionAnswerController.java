package com.roky.thunderspi.controllers.riadh;



import com.roky.thunderspi.entities.QuestionAnswer;
import com.roky.thunderspi.services.riadh.IQuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/qstAns")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionAnswerController {

private final IQuestionAnswerService questionAnswerServiceImp;

@GetMapping("/{id}")
    public QuestionAnswer getQuestionAnswer(@PathVariable Long id)
{
    return questionAnswerServiceImp.findbyId(id);
}

@PostMapping
    public QuestionAnswer addQuestionAnswer(@RequestBody QuestionAnswer q)
{
    return questionAnswerServiceImp.addQuestionAnswer(q);
}

@PutMapping
    public QuestionAnswer editQuestionAnswer(@RequestBody QuestionAnswer q)
{
    return questionAnswerServiceImp.updateQuestionAnswer(q);
}


@DeleteMapping
    public void deleteQuestionAnswer(@RequestBody QuestionAnswer q)
{
    questionAnswerServiceImp.deleteQuestionAnswer(q.getId());
}
@GetMapping
    public Set<QuestionAnswer> getAllQuestionAnswer()
{
    return questionAnswerServiceImp.findAll();
}




}
