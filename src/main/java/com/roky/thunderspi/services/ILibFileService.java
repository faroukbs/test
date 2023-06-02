package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.LibFile;
import com.roky.thunderspi.entities.ProjectFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

public interface ILibFileService {

    public LibFile store(MultipartFile file ) throws IOException;

    public LibFile getFile(Long id);

    public Stream<LibFile> getAllFiles();
    public Set<LibFile> getAllFilesByLib(Long id);
    public Set<LibFile> getAllFilesByLibSubmission(Long id);
}
