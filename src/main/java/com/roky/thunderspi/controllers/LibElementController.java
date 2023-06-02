package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.Course;
import com.roky.thunderspi.entities.LibCategory;
import com.roky.thunderspi.entities.LibElement;
import com.roky.thunderspi.entities.LibFile;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.LibCategoryrepo;
import com.roky.thunderspi.repositories.LibElementRepo;
import com.roky.thunderspi.repositories.LibFileRepository;
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

@RestController
@RequestMapping("/lib")
@AllArgsConstructor
@CrossOrigin("*")
public class LibElementController {


    private ILibElementService iLibElementService;
    private LibFileServiceImpl libFileService;
    @Autowired
    LibElementRepo libElementRepo;
    @Autowired
    LibCategoryrepo libCategoryrepo;
    @Autowired
    LibFileRepository libFileRepository;

    @GetMapping("/getAll")
    public List<LibElement> findAllLibElements() {
        return iLibElementService.findAllLibEle();
    }

    @GetMapping("getAll/{id}")
    public LibElement findLibEleById(@PathVariable Long id) {
        return iLibElementService.findLibEleById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLibEle(@RequestParam MultipartFile file, LibElement libElement) {
        String message = "";
        try {
            libFileService.store(file);
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            LibFile libFile = new LibFile(filename, file.getContentType(), file.getBytes());
            libFileRepository.save(libFile);
            libElement.setFileName(filename);
            libElement.setFileType(file.getContentType());
            libElement.setId_file(libFile.getId());
            message = "Uploaded File successfully: " + file.getOriginalFilename();
            libElementRepo.save(libElement);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/update/{id}")
    public LibElement editLibElement(@RequestBody LibElement libElement, @PathVariable Long id) {
        libElementRepo.findById(id);
        libElement.setName(libElement.getName());
        return iLibElementService.addLibEle(libElement);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLibEle(@PathVariable Long id) {
        iLibElementService.deleteLibEle(id);
    }
}
