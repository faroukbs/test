package com.roky.thunderspi.controllers.riadh;



import com.roky.thunderspi.entities.ProjectFile;
import com.roky.thunderspi.message.ResponseFile;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.services.riadh.IProjectFileServiceImp;
import com.roky.thunderspi.services.riadh.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projectFile")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectFIleController {


    private final IProjectFileServiceImp projectFileService;

    private final IProjectService IProjectServiceImp;


    //TODO Change with before and after annotations to handle the file upload errors
    //Upload without project linked
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadProjectFile(@RequestParam(value ="file")MultipartFile file)
    {
        String message = "";

                try{
            projectFileService.store(file);

            message = "Uploaded File successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));}
            catch (Exception e)
                {
                    message = "Could not upload file: " + file.getOriginalFilename() + "!";
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
                }



    }

    //Upload with project linked
    @PostMapping("/upload{id}")
    public ResponseEntity<ResponseMessage> uploadProjectFileToProject(@RequestParam("file")MultipartFile file,@PathVariable("id")Long id)
    {
        String message = "";
            try{
                projectFileService.storeToProject(file,id);

                message = "Uploaded File successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));}
            catch (Exception e)
            {
                message = "Could not upload file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }


    }
    @PostMapping("/uploadSub{id}")
    public ResponseEntity<ResponseMessage> uploadProjectFileToProjectSub(@RequestParam("file")MultipartFile file,@PathVariable("id")Long id)
    {
        String message = "";
        try{
            projectFileService.storeToProjectSub(file,id);

            message = "Uploaded File successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));}
        catch (Exception e)
        {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }


    }

    //Get All Files
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListProjectFiles(){
        List<ResponseFile> files = projectFileService.getAllFiles().map(projectFile ->
        {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(projectFile.getId().toString())
                    .toUriString();
            return new ResponseFile(projectFile.getName(),fileDownloadUri,projectFile.getType(),projectFile.getData().length);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable Long id)
    {
        ProjectFile projectFile = projectFileService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + projectFile.getName() + "\"")
                .body(projectFile.getData());
    }


    //Get All Files by Project Id
    @GetMapping("/project/{id}")
    public ResponseEntity<List<ResponseFile>> getFilesByProjectId(@PathVariable Long id)
    {
        List<ResponseFile> files = IProjectServiceImp.getProjectById(id).getProjectFiles().stream().map(projectFile ->
        {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(projectFile.getId().toString())
                    .toUriString();
            return new ResponseFile(projectFile.getName(),fileDownloadUri,projectFile.getType(),projectFile.getData().length);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    //Get Project Files by user ID, it uses the get ProjectsByTeacher method in IProjectService
    //However it lacks the retrieval of the files that aren't linked to projects
    @GetMapping("/project/{userId}")
    public ResponseEntity<List<ResponseFile>> getProjectFilesByUserId(@PathVariable Long userId)
    {
        List<ResponseFile> files = IProjectServiceImp.getProjectsByTeacher(userId).flatMap(p->p.getProjectFiles().stream()).map(projectFile ->
        {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(projectFile.getId().toString())
                    .toUriString();
            return new ResponseFile(projectFile.getName(),fileDownloadUri,projectFile.getType(),projectFile.getData().length);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
