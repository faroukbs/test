package com.roky.thunderspi.services;

import com.roky.thunderspi.dto.PostDto;
import com.roky.thunderspi.entities.Post;

import java.util.List;

public interface IBlogPostService {
    List<Post> findAllPosts();


    Post findPostsById(Long postId);
    Post addPost(Post post);
    Post editPost(Post p,Long postId);
    void deletePost(Long postId);

    public void addLike(Long postId, Long idUser);
    public void addDislike(Long postId, Long idUser);
    public int nbrLikeByPub(Long postId);
    public int nbrDisLikeByPub(Long postId);


}
