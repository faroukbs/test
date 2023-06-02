package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.Comment;

import java.util.List;
import java.util.Set;

public interface ICommentService {

    List<Comment> retrieveAllCommentaires();
    public Comment addComment(Comment comment,Long postId,Long iduser);


    void deleteCommentaire(Long id);
    Comment updateCommentaire(Comment cmt,Long cmtid);
    Comment retrieveCommentaire(Long id);
    Set<Comment> getcmtbypost(Long idpost);}
