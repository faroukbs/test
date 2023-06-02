package com.roky.thunderspi.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float maxMarks;

    @ManyToOne
    @JoinColumn(name = "course_id_course")
    private Course course;

    @OneToMany
    private Set<QuizCategory> quizCategories = new LinkedHashSet<>();

    private Date dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(float maxMarks) {
        this.maxMarks = maxMarks;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "quiz", orphanRemoval = true)
    private Set<Question> questions = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<QuizCategory> getQuizCategories() {
        return quizCategories;
    }

    public void setQuizCategories(Set<QuizCategory> quizCategories) {
        this.quizCategories = quizCategories;
    }
}
