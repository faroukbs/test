package com.roky.thunderspi.controllers.riadh;


import com.roky.thunderspi.entities.UserQuizTake;
import com.roky.thunderspi.services.riadh.IUserQuizTakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userQuizTake")
@CrossOrigin("*")
public class UserQuizTakeController {
    private final IUserQuizTakeService userQuizTakeService;



    @GetMapping
    public Set<UserQuizTake> getAll()
    {
        return userQuizTakeService.findAll();
    }

    @GetMapping("/{id}")
    public UserQuizTake getById(@PathVariable("id") Long id)
    {
        return userQuizTakeService.findById(id);
    }

    @PostMapping
    public UserQuizTake add(@RequestBody UserQuizTake userQuizTake)
    {
        return userQuizTakeService.addQuizTake(userQuizTake);
    }

    @PutMapping
    public UserQuizTake update(@RequestBody UserQuizTake userQuizTake)
    {
        return userQuizTakeService.updateQuizTake(userQuizTake);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id)
    {
        userQuizTakeService.deleteQuizTake(id);
    }


    @GetMapping("/user/{id}")
    public Set<UserQuizTake> getQuizTakesByUser(@PathVariable("id") Long id)
    {
        return userQuizTakeService.getQuizTakesByUser(id);
    }


}
