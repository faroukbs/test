package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.FileDB;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.repositories.FileDBRepository;
import com.roky.thunderspi.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


@Service
public class FileStorageService {
	Long idf;
  @Autowired
  private FileDBRepository fileDBRepo;

  @Autowired
  PostRepo postrepo;
  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
    return fileDBRepo.save(FileDB);
  }public Long store1(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
	    fileDBRepo.save(FileDB);
	    return FileDB.getId();
	  }
  public void deletefile(Long idfile,Long idAticle) {
	  FileDB f =fileDBRepo.findById(idfile).orElse(null);
	  Post t=postrepo.findById(idAticle).orElse(null);
	  t.setFiles(null);
	  fileDBRepo.delete(f);
  }
  public FileDB getFile(Long id) {
    return fileDBRepo.findById(id).orElse(null);
  }
  
  
  public Stream<FileDB> getAllFiles() {
    return fileDBRepo.findAll().stream();
  }
  public FileDB getFileBypost(Long id) {
	  Post t =postrepo.findById(id).orElse(null);
	    return t.getFiles();
	  }
  public void affecterFileTopost(Long idFiles, Long idAticle) {
		Post t=postrepo.findById(idAticle).orElse(null);
		
		FileDB f=fileDBRepo.findById(idFiles).orElse(null);
		t.setFiles(f);
		postrepo.save(t);	
	}


 
}