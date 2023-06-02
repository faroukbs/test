package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.Project;
import com.roky.thunderspi.repositories.CourseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
@Service

public class CourseServiceImpl implements ICourseService{
    private final CourseRepo courseRepo;

    @Override
    public List<Course> findAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Course findCouresById(Long id) {
        return courseRepo.findById(id).orElse(null);
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course editCourses(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public void deleteCourses(Long id) {
        courseRepo.deleteById(id);

    }


}
