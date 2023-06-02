package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.LibElement;
import com.roky.thunderspi.repositories.LibElementRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LibElementServiceImpl implements ILibElementService{
    private final LibElementRepo libElementRepo;
    @Override
    public List<LibElement> findAllLibEle() {
        return libElementRepo.findAll();
    }

    @Override
    public LibElement findLibEleById(Long id) {
        return libElementRepo.findById(id).orElse(null);
    }

    @Override
    public LibElement addLibEle(LibElement libElement) {
        return libElementRepo.save(libElement);
    }

    @Override
    public LibElement editLibEle(LibElement libElement) {
        return libElementRepo.save(libElement);
    }

    @Override
    public void deleteLibEle(Long id) {
        libElementRepo.deleteById(id);

    }
}
