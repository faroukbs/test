package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.entities.Product;


import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
    Product findProdById(Long id);
    long addProduct(Product product);
    long editProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getAllProductByCategory(CategoryProduct category);
    void calculeEtoile(Double rev, Long idP, Long idC) throws Exception;

    List<Product>findByPrice(BigDecimal minP, BigDecimal maxP);
}
