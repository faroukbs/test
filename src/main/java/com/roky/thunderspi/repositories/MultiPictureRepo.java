package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.MultiPicture;
import com.roky.thunderspi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MultiPictureRepo extends JpaRepository<MultiPicture,Long> {
    ArrayList<MultiPicture> findByImage(Product product);
}
