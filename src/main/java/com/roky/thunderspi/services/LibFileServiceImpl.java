package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.LibFile;
import com.roky.thunderspi.entities.ProjectFile;
import com.roky.thunderspi.repositories.LibFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;
@Service


public class LibFileServiceImpl implements ILibFileService {
    @Autowired
    private LibFileRepository libFileRepository;


    @Override
    public LibFile store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        LibFile libFile = new LibFile(filename,file.getContentType(), file.getBytes());

        return libFileRepository.save(libFile);
    }

    @Override
    public  LibFile getFile(Long id) {
        return libFileRepository.findById(id).get();
    }


    public Stream<LibFile> getAllFiles() {
        return libFileRepository.findAll().stream();
    }

    @Override
    public Set<LibFile> getAllFilesByLib(Long id) {
        return null;
    }

    @Override
    public Set<LibFile> getAllFilesByLibSubmission(Long id) {
        return null;
    }
}
