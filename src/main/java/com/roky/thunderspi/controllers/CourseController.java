package com.roky.thunderspi.controllers;


import com.roky.thunderspi.entities.*;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.*;
import com.roky.thunderspi.services.ICourseService;
import com.roky.thunderspi.services.ILibElementService;
import com.roky.thunderspi.services.LibFileServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
@CrossOrigin("*")
public class CourseController {
    private ICourseService iCourseService;
    @Autowired
    CourseRepo courseRepo;
    @Autowired
    CourseSubjectRepo courseSubjectRepo;
    @Autowired
    UserRepo userRepo;

    private ILibElementService iLibElementService;
    private LibFileServiceImpl libFileService;
    @Autowired
    LibElementRepo libElementRepo;

    @Autowired
    LibFileRepository libFileRepository;

    @GetMapping("/getAll")
    public List<Course> findAllCourses() {
        return iCourseService.findAllCourses();
    }

    @GetMapping("getAll/{id}")
    public Course findCoursesById(@PathVariable Long id) {
        return iCourseService.findCouresById(id);
    }

    @PostMapping("/addCourse/{iduser}/{idsc}")
    public ResponseEntity<?> addCourse(@RequestParam MultipartFile file, Course course, @PathVariable Long iduser, @PathVariable Long idsc) {
        String message = "";
        try {
            User user = userRepo.findById(iduser).orElse(null);
            CourseSubject courseSubject = courseSubjectRepo.findById(idsc).orElse(null);
            course.setCourseSubject(courseSubject);
            course.setUser(user);
            libFileService.store(file);
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            LibFile libFile = new LibFile(filename, file.getContentType(), file.getBytes());
            libFileRepository.save(libFile);
            course.setFileName(filename);
            course.setFileType(file.getContentType());
            course.setIdFile(libFile.getId());
            course.setVisible(true);
            message = "Uploaded File successfully: " + file.getOriginalFilename();
            courseRepo.save(course);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }

    @PutMapping("/update/{id}")
    public Course editCourse(@RequestBody Course course, @PathVariable Long id) {
        Course ExistantCourse = courseRepo.findById(id).orElseThrow(null);
        ExistantCourse.setCourseLanguage((course.getCourseLanguage()));
        ExistantCourse.setEducationLevel(course.getEducationLevel());
        ExistantCourse.setName(course.getName());
        ExistantCourse.setLength(course.getLength());

        return courseRepo.saveAndFlush(ExistantCourse);
    }

    @PutMapping("/update/rating/{id}")
    public Course editCourseRating(@RequestBody Course course, @PathVariable Long id) {
        Course ExistantCourse = courseRepo.findById(id).orElseThrow(null);
        ExistantCourse.setRating((course.getRating()));
        return courseRepo.saveAndFlush(ExistantCourse);
    }

    @PutMapping("/update/visible/{id}")
    public Course ShowHideCourseRating(@PathVariable Long id) {
        Course ExistantCourse = courseRepo.findById(id).orElseThrow(null);
        if (ExistantCourse.isVisible()){
            ExistantCourse.setVisible(false);
        }else{
            ExistantCourse.setVisible(true);
        }
        return courseRepo.saveAndFlush(ExistantCourse);
    }
    @DeleteMapping("/delete/{idCourse}")
    public void deleteCourse(@PathVariable Long idCourse) {
        iCourseService.deleteCourses(idCourse);
    }


    @GetMapping("/teacher/{id}")
    public List<Course> getCourseByTeacherId(@PathVariable Long id) {
        return courseRepo.getCourseByTeachersId(id);
    }

    @GetMapping("/subject")
    public List<Object> getCourseBySubject() {
        return courseRepo.getCourseBySubject();
    }


}
