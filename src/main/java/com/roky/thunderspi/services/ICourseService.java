package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.Project;

import java.util.List;
import java.util.stream.Stream;

public interface ICourseService  {
    List<Course> findAllCourses();
    Course findCouresById(Long idCourse);
    Course addCourse(Course course);
    Course editCourses(Course course);
    void deleteCourses(Long idCourse);

}
