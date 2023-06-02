package com.roky.thunderspi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.Instant;
import java.util.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;


    private String content;


    private String image;


    private String created_At;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private FileDB files;
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    Set<Comment> comment;

    @OneToMany(mappedBy = "postlike",cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<PostLike> likes;

    @OneToMany(mappedBy = "postdislike",cascade = CascadeType.REMOVE)
    @JsonIgnore
    Set<PostDislike> dislikes;

}
