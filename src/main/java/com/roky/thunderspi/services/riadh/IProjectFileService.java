package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.ProjectFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

public interface IProjectFileService {
    public ProjectFile store(MultipartFile file ) throws IOException;

    public ProjectFile getFile(Long id);

    public Stream<ProjectFile> getAllFiles();
    public Set<ProjectFile> getAllFilesByProject(Long id);
    public Set<ProjectFile> getAllFilesByProjectSubmission(Long id);
}
