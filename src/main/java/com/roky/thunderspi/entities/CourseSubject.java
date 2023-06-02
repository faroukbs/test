package com.roky.thunderspi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class CourseSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCourseSubject;
    String subjectCategory;
    Long idFile;
    int countCourses;
    @OneToMany(mappedBy = "courseSubject",cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<Course> courses;
}
