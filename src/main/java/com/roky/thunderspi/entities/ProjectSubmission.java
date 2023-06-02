package com.roky.thunderspi.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class ProjectSubmission {
  @Id
  private Long id;

  private String title;

  private String description;
  private float marks;

  public float getMarks() {
    return marks;
  }

  public void setMarks(float marks) {
    this.marks = marks;
  }

  public Set<ProjectFile> getProjectFiles() {
    return projectFiles;
  }

  public void setProjectFiles(Set<ProjectFile> projectFiles) {
    this.projectFiles = projectFiles;
  }

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
  private Set<ProjectFile> projectFiles = new LinkedHashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  @JoinColumn(name = "project_id")
  private Project project;

  @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;



}
