package com.roky.thunderspi.controllers;


import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.repositories.CommentRepo;
import com.roky.thunderspi.repositories.PostRepo;
import com.roky.thunderspi.repositories.UserRepo;
import com.roky.thunderspi.services.IBlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",exposedHeaders="Access-Control-Allow-Origin" )
@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    @Autowired
    PostRepo postRepo;
    @Autowired
    CommentRepo commentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    IBlogPostService postService;

   

    @GetMapping("/getAllpost")
    public List<Post> findAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/getpost/{postId}")
    public Post findPostById(@PathVariable Long postId) {
        return postService.findPostsById(postId);
    }


   @PostMapping("/addPost")
     public Post addPost(@RequestBody Post post) {
      return postService.addPost(post);

    }

   /* @PostMapping("/addPost/{iduser}/{idsc}")
    public ResponseEntity<?> addCourse(@RequestParam MultipartFile file, Post post, @PathVariable Long iduser, @PathVariable Long idsc) {
        String message = "";
        try {
            User user = userRepo.findById(iduser).orElse(null);
            Comment comments = commentRepo.findCommentById(idsc).orElse(null);
            post.setComment((List<Comment>) comments);
            post.setUser(user);
            libFileService.store(file);
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            LibFile libFile = new LibFile(filename, file.getContentType(), file.getBytes());
            libFileRepository.save(libFile);
            post.setFileName(filename);
            post.setFileType(file.getContentType());
            post.setIdFile(libFile.getId());
            message = "Uploaded File successfully: " + file.getOriginalFilename();
            postRepo.save(post);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

    }*/

    @PutMapping("/updatepost/{postId}")
    public Post editpost(@RequestBody Post post, @PathVariable Long postId) {

        return postService.editPost(post,postId);
    }

    @DeleteMapping("/deletepost/{postId}")
    public void deleteCourse(@PathVariable Long postId) {
        postService.deletePost(postId);
    }


	@PutMapping("/addLike/{idPublicaiton}/{idUser}")
	public void addLike(@PathVariable("idPublicaiton") Long idPublicaiton, @PathVariable("idUser") Long idUser) {
		postService.addLike(idPublicaiton, idUser);
	}


	@PutMapping("/addDislike/{idPublicaiton}/{idUser}")
	public void addDislike(@PathVariable("idPublicaiton") Long idPublicaiton, @PathVariable("idUser") Long idUser) {
		postService.addDislike(idPublicaiton, idUser);
	}



	@GetMapping("/nbrLike/{idPublicaiton}")
	public int NbrLikes(@PathVariable("idPublicaiton") Long idPublicaiton) {
		return postService.nbrLikeByPub(idPublicaiton);

	}

	
	@GetMapping("/nbrDisLike/{idPublicaiton}")
	public int NbrDisLikes(@PathVariable("idPublicaiton") Long idPublicaiton) {
		return postService.nbrDisLikeByPub(idPublicaiton);

	}



}
