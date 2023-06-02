package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Actuality;
import com.roky.thunderspi.entities.Post;

import java.util.List;

public interface IActualityService {
    void add(Actuality s);

    Actuality update(Actuality s);

    List<Actuality> getAll();

    Actuality getById(long id);

    void remove(long id);
}
