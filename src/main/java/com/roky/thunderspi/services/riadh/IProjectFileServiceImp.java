package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Project;
import com.roky.thunderspi.entities.ProjectFile;
import com.roky.thunderspi.entities.ProjectSubmission;
import com.roky.thunderspi.repositories.riadh.ProjectFileRepository;
import com.roky.thunderspi.repositories.riadh.ProjectRepository;
import com.roky.thunderspi.repositories.riadh.ProjectSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IProjectFileServiceImp implements IProjectFileService {


    private final ProjectFileRepository projectFileRepository;
    private final ProjectRepository projectRepository;

    private final ProjectSubmissionRepository projectSubmissionRepository;

    public ProjectFile store(MultipartFile file ) throws IOException
    {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        ProjectFile projectFile = new ProjectFile(filename,file.getContentType(), file.getBytes());

        return projectFileRepository.save(projectFile);
    }


    @Transactional
    public ProjectFile storeToProject(MultipartFile file, Long id ) throws  IOException
    {
        Assert.notNull(id, "You need to add to an existing project");
        Project p = projectRepository.findById(id).orElse(null);
        Assert.notNull(p, "Project not found");
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        ProjectFile projectFile = new ProjectFile(filename,file.getContentType(), file.getBytes());
        p.getProjectFiles().add(projectFile);
        return projectFileRepository.save(projectFile);
    }
    @Transactional
    public ProjectFile storeToProjectSub(MultipartFile file, Long id ) throws  IOException
    {
        Assert.notNull(id, "You need to add to an existing project");
        ProjectSubmission p = projectSubmissionRepository.findById(id).orElse(null);
        Assert.notNull(p, "Project not found");
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        ProjectFile projectFile = new ProjectFile(filename,file.getContentType(), file.getBytes());
        p.getProjectFiles().add(projectFile);
        return projectFileRepository.save(projectFile);
    }

    public ProjectFile getFile(Long id)
    {
        return projectFileRepository.findById(id).get();
    }

    public Stream<ProjectFile> getAllFiles()
    {
        return projectFileRepository.findAll().stream();
    }

    @Override
    public Set<ProjectFile> getAllFilesByProject(Long id) {
        return null;
    }

    @Override
    public Set<ProjectFile> getAllFilesByProjectSubmission(Long id) {
        return null;
    }
}
