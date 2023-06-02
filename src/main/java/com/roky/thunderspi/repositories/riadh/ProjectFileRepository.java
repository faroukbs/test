package com.roky.thunderspi.repositories.riadh;


import com.roky.thunderspi.entities.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectFileRepository  extends JpaRepository<ProjectFile,Long> {


}
