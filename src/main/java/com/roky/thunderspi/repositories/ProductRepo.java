package com.roky.thunderspi.repositories;

import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findProductById(Long id);

    public List<Product> findByCategory(CategoryProduct category);

    List<Product>findByPrixBetween(BigDecimal minP,BigDecimal maxP);

    @Modifying
    @Query(value="delete from wishlist where product_id = :id",nativeQuery = true)
    void removeFromWishListWhenIsSold(@Param("id") Long id);

    @Modifying
    @Query(value="delete from wishlist where product_id = :productId and client_id = :clientId",nativeQuery = true)
    void removeFromClientWishlist(@Param("productId") Long productId, @Param("clientId") Long clientId);

}
