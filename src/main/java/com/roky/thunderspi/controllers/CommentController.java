package com.roky.thunderspi.controllers;

import com.roky.thunderspi.dto.PostDto;
import com.roky.thunderspi.entities.Comment;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.Product;
import com.roky.thunderspi.entities.ProductComment;
import com.roky.thunderspi.services.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.roky.thunderspi.entities.*;
import com.roky.thunderspi.message.ResponseMessage;
import com.roky.thunderspi.repositories.*;
import com.roky.thunderspi.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@AllArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {


    @Autowired
    PostRepo postRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CommentServiceImpl cmtservice;



    @PostMapping("/addcmt/{idpost}/{iduser}")
    public Comment addCommentaire(@RequestBody Comment comment, @PathVariable("idpost") Long post, @PathVariable("iduser") Long user) {
        return cmtservice.addComment(comment, post, user);
    }



    @GetMapping("/getallcmt")
    @ResponseBody
    public List<Comment> getAll() {

        return cmtservice.retrieveAllCommentaires();
    }

    @GetMapping("/retrieve-commentaire/{commentaire-id}")
    @ResponseBody
    public Comment retrieveCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        return cmtservice.retrieveCommentaire(commentaireId);
    }
    @GetMapping("/getallcmtbypost/{idpost}")
    @ResponseBody
    public Set<Comment> getcmtbypost(@PathVariable("idpost") Long postid) {

        return cmtservice.getcmtbypost(postid);
    }
    @PutMapping("/update-cmt/{commentaire-id}")
    public Comment updateComment(@RequestBody Comment c,@PathVariable("commentaire-id") Long commentaireId) {

        return cmtservice.updateCommentaire(c, commentaireId);
    }
    // http://localhost:8081/remove-commentaire/{commentaire-id}
    @DeleteMapping("/remove-cmt/{commentaire-id}")
    @ResponseBody
    public void removeCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        cmtservice.deleteCommentaire(commentaireId);
    }

    // http://localhost:8081/modify-commentaire

}
