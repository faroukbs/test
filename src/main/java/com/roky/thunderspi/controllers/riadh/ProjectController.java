package com.roky.thunderspi.controllers.riadh;


import com.roky.thunderspi.entities.Project;
import com.roky.thunderspi.services.riadh.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectController {


    private final IProjectService projectService;

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id)
    {
        return projectService.getProjectById(id);
    }

    @PostMapping
    @Transactional //Problem about linking the project files to the project
    //waiting for the project files to be uploaded, getting an ID
    //Having them assigned to the project
    public Project addProject(@RequestBody Project p)
    {
        return projectService.addProject(p);
    }

    @PutMapping
    public void updateProject(@RequestBody Project p)
    {
        projectService.updateProject(p);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id)
    {
        projectService.deleteProject(id);
    }

    @GetMapping("/teacher/{id}")
    public Set<Project> getProjectByTeacherId(@PathVariable Long id)
    {
        return projectService.getProjectsByTeacher(id).collect(Collectors.toSet());
    }

    @GetMapping
    public Set<Project> getAllProjects()
    {
        return projectService.getAllProjects().collect(Collectors.toSet());
    }

}
