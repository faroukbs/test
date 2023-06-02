package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.CourseSubject;
import com.roky.thunderspi.entities.LibElement;
import com.roky.thunderspi.entities.LibFile;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.LibCategoryrepo;
import com.roky.thunderspi.repositories.LibElementRepo;
import com.roky.thunderspi.repositories.LibFileRepository;
import com.roky.thunderspi.services.ICourseSubjectService;
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

@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
@CrossOrigin("*")
public class CourseSubjectController {
    private ILibElementService iLibElementService;
    private LibFileServiceImpl libFileService;

    @Autowired
    LibFileRepository libFileRepository;
    private ICourseSubjectService iCourseSubjectService;

    @GetMapping("/getAll")
    public List<CourseSubject> findAllCoursesSubject() {
      iCourseSubjectService.findAllCoursesSubject()
                .stream()
                .forEach(s -> s.setCountCourses(s.getCourses().size()));
        return iCourseSubjectService.findAllCoursesSubject();
    }

    @GetMapping("getAll/{id}")
    public CourseSubject findCoursesSubjectById(@PathVariable Long id) {
        return iCourseSubjectService.findCouresSubjectById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCoursesSubject(@RequestParam MultipartFile file, CourseSubject courseSubject) {
        String message = "";
        try {
            libFileService.store(file);
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            LibFile libFile = new LibFile(filename, file.getContentType(), file.getBytes());
            libFileRepository.save(libFile);

            courseSubject.setIdFile(libFile.getId());
            message = "Uploaded File successfully: " + file.getOriginalFilename();

            iCourseSubjectService.addCoursesSubject(courseSubject);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/update")
    public CourseSubject editCoursesSubject(@RequestBody CourseSubject courseSubject) {
        return iCourseSubjectService.addCoursesSubject(courseSubject);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCoursesSubject(@PathVariable Long id) {
        iCourseSubjectService.deleteCoursesSubject(id);
    }
}

