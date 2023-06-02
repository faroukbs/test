package com.roky.thunderspi.repositories;


import com.roky.thunderspi.entities.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

;


public interface FileDBRepository extends JpaRepository<FileDB, Long> {
	

}
