package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.Project;
import com.roky.thunderspi.entities.ProjectFile;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.riadh.ProjectFileRepository;
import com.roky.thunderspi.repositories.riadh.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class IProjectServiceImp implements IProjectService{

    private final ProjectRepository projectRepository;
    private final ProjectFileRepository projectFileRepository;

    @Transactional
    public Project addProject(Project p)
    {
        //Need to implement nested Object Addition
        Set<ProjectFile> projectFileSet = p.getProjectFiles();
       // projectFileSet.forEach(file ->  {
       //     String filename = StringUtils.cleanPath(file.getOriginalFilename());
       //     ProjectFile projectFile = new ProjectFile(filename,file.getContentType(), file.getBytes());
//
       //     return projectFileRepository.save(projectFile);
       // });
        //Course course = p.getCourse();
        //User teacher = p.getUser();

        //Assert.notNull(teacher, "Project needs to be created by teacher");
        return projectRepository.save(p);
    }
    public Project updateProject(Project p)
    {
        Assert.notNull(projectRepository.findById(p.getId()));
        return projectRepository.save(p);
    }

    public void deleteProject(Long id)
    {
        projectRepository.deleteById(id);
    }

    public Project getProjectById(Long id)
    {
        return projectRepository.findById(id).get();
    }

    public Stream<Project> getAllProjects()
    {
        return projectRepository.findAll().stream();
    }


    public Stream<Project> getProjectsByTeacher(Long id){ return null;}

    @Override
    public Stream<Project> getProjectsByNumberOfNumberPassed() {
        return null;
    }

    @Override
    public Stream<Project> getProjectsByNumberOfEnrollments() {
        return null;
    }

    @Override
    public Stream<Project> getProjectsByCourse(Long courseId) {
        return null;
    }
}
