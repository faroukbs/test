package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo  extends JpaRepository<Comment,Long> {
    List<Comment> findAll();
}
