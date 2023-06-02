package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.ProductComment;

import java.util.List;

public interface IProductCommentService {
     List<ProductComment> retrieveAllCommentaires();

     ProductComment addCommentaire(ProductComment c);

     void deleteCommentaire(Long id);
     ProductComment updateCommentaire(ProductComment u);
     ProductComment retrieveCommentaire(Long id);
}