package com.roky.thunderspi.controllers;


import com.roky.thunderspi.entities.Product;
import com.roky.thunderspi.entities.ProductComment;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.services.IProductCommentService;
import com.roky.thunderspi.services.IProductService;
import com.roky.thunderspi.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
@CrossOrigin("*")
public class ProductCommentController {
    private IProductCommentService commentaireService;
    private IProductService produitService;
    private UserServiceImpl userService;

    @GetMapping("/retrieve-all-commentaires")
    @ResponseBody
    public List<ProductComment> getCommentaire() {
        List<ProductComment> listCommentaires = commentaireService.retrieveAllCommentaires();
        return listCommentaires;
    }

    @GetMapping(path="/getUser/{id}")
    public User getUSerCommentaire(@PathVariable("id") Long id) throws Exception {
        User u = userService.findById(commentaireService.retrieveCommentaire(id).getIdClient());
        return u;
    }

    // http://localhost:8081/add-commentaire
    @PostMapping("/add-commentaire")
    @ResponseBody
    public ProductComment addCommentaire(@RequestBody ProductComment c) throws Exception{
        List<String> badWords= Collections.unmodifiableList(Arrays.asList("bob","fuck","shit","dick","sh*t","ass","bitch","bastard","cunt","trash","wanker","piss","pussy","twat","crap","arsehole","gash","prick","cock","minge","nigga","slut","damn","sucker","cracker","poop","puup","boob","buub","f*ck","b*tch","3asba","nayek","nikomok","9a7ba","zebi","sorm"));
        Product p=produitService.findProdById(c.getProcom().getId());

        if(p==null){throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "NULL");}


        if(c.getComment().replaceAll("\\s+","").equals("")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Empty");
        }

        for(String bW : badWords){
            if(c.getComment().toLowerCase().replaceAll("\\s+","").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g").contains(bW)){
                throw new ResponseStatusException( HttpStatus.NOT_ACCEPTABLE, "Bad Boy");}
        }

        for(ProductComment com :p.getComments()){
            if(c.getComment().equals(com.getComment())){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duplicated");
            }
        }



        return commentaireService.addCommentaire(c);
    }

    // http://localhost:8081/retrieve-commentaire/2
    @GetMapping("/retrieve-commentaire/{commentaire-id}")
    @ResponseBody
    public ProductComment retrieveCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        return commentaireService.retrieveCommentaire(commentaireId);
    }


    // http://localhost:8081/remove-commentaire/{commentaire-id}
    @DeleteMapping("/remove-client/{commentaire-id}")
    @ResponseBody
    public void removeCommentaire(@PathVariable("commentaire-id") Long commentaireId) {
        commentaireService.deleteCommentaire(commentaireId);
    }

    // http://localhost:8081/modify-commentaire
    @PutMapping("/modify-commentaire")
    @ResponseBody
    public ProductComment modifyClient(@RequestBody ProductComment c) throws Exception {
        List<String> badWords=Collections.unmodifiableList(Arrays.asList("bob","fuck","shit","dick","sh*t","ass","bitch","bastard","cunt","trash","wanker","piss","pussy","twat","crap","arsehole","gash","prick","cock","minge","nigga","slut","damn","sucker","cracker","poop","puup","boob","buub","f*ck","b*tch"));
        Product p=produitService.findProdById(c.getProcom().getId());

        if(p==null){throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "NULL");}


        if(c.getComment().replaceAll("\\s+","").equals("")){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Empty");
        }

        for(String bW : badWords){
            if(c.getComment().toLowerCase().replaceAll("\\s+","").replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a").replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g").contains(bW)){throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Bad Boy");}
        }

        for(ProductComment com :p.getComments()){
            if(c.getComment().equals(com.getComment()) && c.getLikes()==com.getLikes()){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Duplicated");
        }
        }
        return commentaireService.updateCommentaire(c);
    }

}
