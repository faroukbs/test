package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.Project;

import java.util.stream.Stream;

public interface IProjectService {
    public Project addProject(Project p);
    public Project updateProject(Project p);
    public void deleteProject(Long id);

    public Project getProjectById(Long id);

    public Stream<Project> getAllProjects();

    public Stream<Project> getProjectsByTeacher(Long id);

    public Stream<Project> getProjectsByNumberOfNumberPassed();

    public Stream<Project> getProjectsByNumberOfEnrollments();

    public Stream<Project> getProjectsByCourse(Long courseId);


}
