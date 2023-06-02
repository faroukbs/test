package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.UserQuizTake;
import com.roky.thunderspi.repositories.riadh.UserQuizTakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IUserQuizTakeServiceImp implements IUserQuizTakeService{
    private final UserQuizTakeRepository userQuizTakeRepository;

    @Override
    public UserQuizTake addQuizTake(UserQuizTake take) {
      return  userQuizTakeRepository.save(take);
    }

    @Override
    public UserQuizTake updateQuizTake(UserQuizTake take) {
        UserQuizTake userQuizTake = userQuizTakeRepository.findById(take.getId()).orElse(null);
        if(userQuizTake != null){
            return userQuizTake;
        }else return null;
    }

    @Override
    public Set<UserQuizTake> findAll() {
        return userQuizTakeRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Set<UserQuizTake> findByQuiz(Long quizId) {
        return userQuizTakeRepository
                .findAll().stream().filter(userQuizTake -> userQuizTake.getQuiz().getId().equals(quizId)).collect(Collectors.toSet());
    }

    @Override
    public void deleteQuizTake(Long id) {
        userQuizTakeRepository.deleteById(id);
    }

    @Override
    public UserQuizTake findById(Long id) {
        return userQuizTakeRepository.findById(id).get();
    }

    @Override
    public Set<UserQuizTake> getQuizTakesByUser(Long id) {
        return userQuizTakeRepository.findAll().stream().filter(userQuizTake -> userQuizTake.getUser().getId().equals(id)).collect(Collectors.toSet());
    }

}
