package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.Product;
import com.roky.thunderspi.services.WishListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RestController
@RequestMapping("/favoriteProduct")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishListController {
    @Autowired
    private WishListServiceImpl service;


    @GetMapping("/wishlist")
    public ResponseEntity<Set<Product>> returnWishlist() throws Exception {


        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/wishlist/{productId}")
    public ResponseEntity<Void> maskProductAsWished(@PathVariable Long productId) throws Exception {

        service.markProductAsWished(productId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dwishlist/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId) {
        service.delete(productId);

        return ResponseEntity.noContent().build();
    }
}
