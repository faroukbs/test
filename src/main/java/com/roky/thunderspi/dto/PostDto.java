package com.roky.thunderspi.dto;

import com.roky.thunderspi.entities.Comment;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.services.AuthenticationService;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PostDto {

    private Long id;
    private String content;
    private String title;
    private String userName;
    private String image;
    private User user;
    private Set<Comment> comments;
    private String created_at;
    private String updated_at;




}
