package com.roky.thunderspi.controllers;

import com.roky.thunderspi.dto.PostDto;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.exception.ResourceNotFoundException;
import com.roky.thunderspi.repositories.PostRepo;
import com.roky.thunderspi.services.BlogPostServiceImpl;
import com.roky.thunderspi.services.IBlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class BlogPostController {

  /*  @Autowired
    private BlogPostServiceImpl postService;
private PostRepo postRepo;

    @PostMapping("/add")
    public ResponseEntity createPost(@RequestBody PostDto postDto){

        postService.createPost(postDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts(){
        return new ResponseEntity<>(postService.showAllPosts(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id){

        return  new ResponseEntity<>(postService.readSinglePost(id),HttpStatus.OK);
    }
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity update(@RequestBody Post post) {
        this.postService.savePost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + id));

        postRepo.delete(post);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }*/
}