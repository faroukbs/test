package com.roky.thunderspi.services.riadh;


import com.roky.thunderspi.entities.ProjectSubmission;

import java.util.Set;

public interface IProjectSubmissionService {
    public ProjectSubmission addProjectSubmission(ProjectSubmission p);

    public ProjectSubmission updateProjectrSubmission(ProjectSubmission p);

    public void deleteProjectSubmission(Long id);

    public ProjectSubmission getProjectSubmissionById(Long id);

    public Set<ProjectSubmission> getAllProjectSubmissions();
    public Set<ProjectSubmission> getProjectSubmissionsByStudentId(Long id);
    public Set<ProjectSubmission> getProjectSubmissionByGrades();


    Set<ProjectSubmission> getProjectSubmissionsByProjectId(Long id);
}
