package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.LibCategory;

import java.util.List;

public interface ILibCategoryService {

    List<LibCategory> findAllLibCat();
    LibCategory findLibCatById(Long id);
    LibCategory addLibCat(LibCategory libCategory);
    LibCategory editLibCat(LibCategory libCategory);
    void deleteLibCat(Long id);
}
