package com.roky.thunderspi.services;

import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.repositories.CategoryProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements  IProductCategoryService{
    private CategoryProductRepo categoryProductRepo;
    @Override
    public List<CategoryProduct> findAllCategory() {
        return categoryProductRepo.findAll();
    }

    @Override
    public CategoryProduct findProdById(Long id) {
        return categoryProductRepo.findById(id).orElse(null);
    }

    @Override
    public CategoryProduct addProduct(CategoryProduct categoryProduct) {
        return categoryProductRepo.save(categoryProduct);
    }

    @Override
    public CategoryProduct editProduct(CategoryProduct categoryProduct) {
        return categoryProductRepo.save(categoryProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        categoryProductRepo.deleteById(id);
    }
}
