package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.ProductComment;
import com.roky.thunderspi.repositories.ProductCommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCommentServiceImpl implements IProductCommentService{

    private ProductCommentRepo productCommentRepo;

    @Override
    public List<ProductComment> retrieveAllCommentaires() {
        List<ProductComment> commentaires = (List<ProductComment>) productCommentRepo.findAll();

        return commentaires;
    }

    @Override
    public ProductComment addCommentaire(ProductComment c) {
        productCommentRepo.save(c);
        return c;
    }

    @Override
    public void deleteCommentaire(Long id) {
        productCommentRepo.deleteById(id);
    }

    @Override
    public ProductComment updateCommentaire(ProductComment u) {
        productCommentRepo.save(u);
        return u;
    }

    @Override
    public ProductComment retrieveCommentaire(Long id) {
        ProductComment commentaire = productCommentRepo.findById(id).orElse(null);
        return commentaire;
    }
}
