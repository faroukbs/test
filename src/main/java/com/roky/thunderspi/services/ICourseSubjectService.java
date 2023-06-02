package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.CourseSubject;

import java.util.List;

public interface ICourseSubjectService {
    List<CourseSubject> findAllCoursesSubject();
    CourseSubject findCouresSubjectById(Long id);
    CourseSubject addCoursesSubject(CourseSubject courseSubject);
    CourseSubject editCoursesSubject(CourseSubject courseSubject);
    void deleteCoursesSubject(Long id);
}
