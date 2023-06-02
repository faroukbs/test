package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.CategoryProduct;
import com.roky.thunderspi.services.IProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoryProductController {
    private IProductCategoryService iProductCategoryService;


    @GetMapping("/all")
    public ResponseEntity<List<CategoryProduct>> getAllCategories () {
        List<CategoryProduct> category = iProductCategoryService.findAllCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryProduct> getCategoryById (@PathVariable("id") Long id) throws Exception {
        CategoryProduct category = iProductCategoryService.findProdById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryProduct> addCategory(@RequestBody CategoryProduct category) {
        CategoryProduct newCategory = iProductCategoryService.addProduct(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryProduct> updateCategory(@RequestBody CategoryProduct category) {
        CategoryProduct updateCategory = iProductCategoryService.editProduct(category);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        iProductCategoryService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
