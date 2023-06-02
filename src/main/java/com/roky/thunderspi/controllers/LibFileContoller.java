package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.LibFile;

import com.roky.thunderspi.message.ResponseFile;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.LibFileRepository;
import com.roky.thunderspi.services.ILibFileService;
import com.roky.thunderspi.services.LibFileServiceImpl;

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
@RequestMapping("/api/libFile")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LibFileContoller {
    private final ILibFileService LibFileServiceImpl;

    private final LibFileServiceImpl libFileService;
    @Autowired
    LibFileRepository libFileRepository;
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadLibFile(@RequestParam("file") MultipartFile file,LibFile libFile)
    {
        String message = "";
        try {
            libFileService.store(file);

            message = "Uploaded File successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch (Exception e)
        {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListLibFiles(){
        List<ResponseFile> files = libFileService.getAllFiles().map(libFile->
        {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(libFile.getId().toString())
                    .toUriString();
            return new ResponseFile(libFile.getName(),fileDownloadUri,libFile.getType(),libFile.getData().length);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable Long id)
    {
        LibFile libFile = libFileService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + libFile.getName() + "\"")
                .body(libFile.getData());
    }

   /* @GetMapping("/lib/{id}")
    public ResponseEntity<List<ResponseFile>> getFilesByLibId(@PathVariable Long id)
    {
        List<ResponseFile> files = LibFileServiceImpl.getFile(id).getLibFiles().stream().map(libFile ->
        {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files")
                    .path(libFile.getId().toString())
                    .toUriString();
            return new ResponseFile(libFile.getName(),fileDownloadUri,libFile.getType(),libFile.getData().length);

        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }*/

}
