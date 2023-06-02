package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.LibCategory;
import com.roky.thunderspi.entities.LibElement;

import java.util.List;

public interface ILibElementService {
    List<LibElement> findAllLibEle();
    LibElement findLibEleById(Long id);
    LibElement addLibEle(LibElement libElement);
    LibElement editLibEle(LibElement libElement);
    void deleteLibEle(Long id);
}
