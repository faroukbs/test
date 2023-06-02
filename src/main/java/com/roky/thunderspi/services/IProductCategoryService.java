package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.CategoryProduct;

import java.util.List;

public interface IProductCategoryService {
    public List<CategoryProduct> findAllCategory();

    public CategoryProduct findProdById(Long id);

    public CategoryProduct addProduct(CategoryProduct categoryProduct);

    public CategoryProduct editProduct(CategoryProduct categoryProduct);

    public void deleteProduct(Long id);


}
