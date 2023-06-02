package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.PostDislike;
import com.roky.thunderspi.entities.PostLike;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.DislikeRepository;
import com.roky.thunderspi.repositories.LikeRepository;
import com.roky.thunderspi.repositories.PostRepo;
import com.roky.thunderspi.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;

import org.jsoup.Jsoup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class BlogPostServiceImpl implements IBlogPostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private LikeRepository likeRepo;
    @Autowired
    private DislikeRepository dislikeRepo;

    @Autowired
    UserRepo utiRepo;


    @Override
    public List<Post> findAllPosts() {
        List pl = new ArrayList<Post>();
        for(Post p :postRepo.findAll() ){
            p.setContent(convert(p.getContent()));
            pl.add(p);
        }
        return pl;
    }

    public static String convert(String html) {
        Document document = (Document) Jsoup.parse(html);
        String text = document.text();
        return text;
    }

    @Override
    public Post findPostsById(Long postId) {
        Post p=postRepo.findById(postId).orElse(null);
        //	p.setContent(convert(p.getContent()));
        return p;

    }

    @Override
    public Post addPost(Post post) {
        Instant instant = Instant.now(); // Obtenir l'instant actuel
        ZoneId zoneId = ZoneId.systemDefault(); // Obtenir le fuseau horaire par défaut
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(zoneId); // Créer un formateur de date/heure

        String formattedDate = formatter.format(instant); // Convertir l'instant en une chaîne de caractères formatée
        post.setCreated_At(formattedDate);
        return postRepo.save(post);
    }

    @Override
    public Post editPost(Post post,Long postid) {
        Post p = postRepo.findById(postid).orElse(null);
        p.setContent(post.getContent());
        p.setTitle(post.getTitle());

        return postRepo.save(p);
    }

    @Override
    public void deletePost(Long postId) {
        postRepo.deleteById(postId);

    }

    ///////////// Like & Dislike//////////
    @Override
    public void addLike(Long idPublicaiton, Long idUser) {
        log.info("idPublicaiton"+idPublicaiton+"idUser"+idUser);
        PostLike lk = new PostLike();
        Post publication = postRepo.findById(idPublicaiton).orElse(null);
        User user = utiRepo.findById(idUser).orElse(null);
        PostLike like = likeRepo.findByPostlikeAndUtilis(publication, user);
        log.info("like"+like);
        PostDislike dislike = dislikeRepo.findByPostdislikeAndUtilis(publication, user);
        log.info("dislike"+dislike);
        lk.setPostlike(publication);
        lk.setUtilis(user);
        if (like == null && dislike == null) {
            likeRepo.save(lk);
        } else if (like == null && dislike != null) {
            dislike.setPostdislike(null);
            dislike.setUtilis(null);
            dislikeRepo.deleteById(dislike.getIdDisLike());
            likeRepo.save(lk);
        } else {
            likeRepo.delete(like);
        }
    }

    @Override
    public void addDislike(Long idPublicaiton, Long idUser) {
        PostDislike lk = new PostDislike();
        Post publication = postRepo.findById(idPublicaiton).orElse(null);
        User user = utiRepo.findById(idUser).orElse(null);
        PostLike like = likeRepo.findByPostlikeAndUtilis(publication, user);
        log.info("like"+like);
        PostDislike dislike = dislikeRepo.findByPostdislikeAndUtilis(publication, user);
        log.info("dislike"+dislike);
        lk.setPostdislike(publication);
        lk.setUtilis(user);
        if (like == null && dislike == null) {
            dislikeRepo.save(lk);
        } else if (dislike == null && like != null) {
            like.setPostlike(null);
            like.setUtilis(null);
            likeRepo.deleteById(like.getIdLike());
            dislikeRepo.save(lk);
        } else {
            dislikeRepo.delete(dislike);
        }
    }

    /////////////////////////Nombres Like , Dislikes , Comments////////////////
    @Override
    public int nbrLikeByPub(Long idPublicaiton) {
        Post publication = postRepo.findById(idPublicaiton).orElse(null);
        return publication.getLikes().size();
    }

    @Override
    public int nbrDisLikeByPub(Long idPublicaiton) {
        Post publication = postRepo.findById(idPublicaiton).orElse(null);
        return publication.getDislikes().size();
    }

}
