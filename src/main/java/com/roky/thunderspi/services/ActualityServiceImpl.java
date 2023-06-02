package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Actuality;
import com.roky.thunderspi.repositories.ActualityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualityServiceImpl implements IActualityService{

    @Autowired
    ActualityRepo actualityRepo;
    @Override

    public void add(Actuality s) {
        actualityRepo.save(s);
    }

    @Override
    public Actuality update(Actuality s) {
        return actualityRepo.save(s);
    }

    @Override
    public List<Actuality> getAll() {
        return (List<Actuality>) actualityRepo.findAll();
    }

    @Override
    public Actuality getById(long id) {
        return actualityRepo.findById(id).orElse(null);
    }

    @Override
    public void remove(long id) {

        actualityRepo.deleteById(id);
    }
}
