package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepo extends JpaRepository<ProductComment,Long> {

}
