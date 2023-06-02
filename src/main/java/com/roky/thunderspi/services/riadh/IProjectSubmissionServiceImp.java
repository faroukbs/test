package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.ProjectSubmission;
import com.roky.thunderspi.repositories.riadh.ProjectSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IProjectSubmissionServiceImp implements IProjectSubmissionService{

    @Autowired
    private ProjectSubmissionRepository projectSubmissionRepository;

    public ProjectSubmission addProjectSubmission(ProjectSubmission p)
    {
        return projectSubmissionRepository.save(p);
    }

    public ProjectSubmission updateProjectrSubmission(ProjectSubmission p){return projectSubmissionRepository.save(p);}

    public void deleteProjectSubmission(Long id)
    {
        projectSubmissionRepository.deleteById(id);
    }

    public ProjectSubmission getProjectSubmissionById(Long id)
    {
        return projectSubmissionRepository.findById(id).get();
    }

    public Set<ProjectSubmission> getAllProjectSubmissions()
    {
        return  projectSubmissionRepository.findAll().stream().collect(Collectors.toSet());
    }

    public Set<ProjectSubmission> getProjectSubmissionsByStudentId(Long id)
    {
        return projectSubmissionRepository.findByUserId(id);
    }

    @Override
    public Set<ProjectSubmission> getProjectSubmissionByGrades() {
        return projectSubmissionRepository.findAll()
                .stream()
                .sorted((o1, o2) -> {
                    if(o1.getMarks() == o2.getMarks())
                        return o1.getId().compareTo(o2.getId());
                    else if(o1.getMarks() > o2.getMarks())
                        return 1;
                    else return -1;
                }).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<ProjectSubmission> getProjectSubmissionsByProjectId(Long id) {
        return projectSubmissionRepository.findAllByProjectId(id);
    }


}
