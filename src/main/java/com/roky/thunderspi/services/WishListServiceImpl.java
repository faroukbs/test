package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.Product;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.ProductRepo;
import com.roky.thunderspi.repositories.UserRepo;
import com.roky.thunderspi.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class WishListServiceImpl {

    @Autowired
    private UserServiceImpl clientService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private UserRepo clientRepo;

    @Autowired
    private ProductRepo productRepo;

    private User findClientById(Long id) throws Exception {
        return clientService.findById(id);
    }

    private Product findProductById(Long id) throws Exception {
        return productService.findProdById(id);
    }

    public Set<Product> findAll() throws Exception {
        UserPrincipal user = UserServiceImpl.clientAuthenticated();
        User cli = findClientById(user.getId());

        return cli.getProductsWished();

    }

    public void markProductAsWished(Long productId) throws Exception {
        Product product = findProductById(productId);
        UserPrincipal user = UserServiceImpl.clientAuthenticated();
        User client = findClientById(user.getId());

        if (client.getProductsWished().contains(product)) {
            throw new Exception();
        }

        client.getProductsWished().add(product);
        product.getWhoWhishesThisProduct().add(client);

        clientRepo.save(client);
        productRepo.save(product);
    }

    @Transactional
    public void delete(Long productId) {
        UserPrincipal user = UserServiceImpl.clientAuthenticated();

        productRepo.removeFromClientWishlist(productId, user.getId());
    }

    @Transactional
    public void removeProductFromWishlistWhenIsSold(Long productId) {
        productRepo.removeFromWishListWhenIsSold(productId);
    }
}