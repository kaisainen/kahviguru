package com.example.nkk.services;

import java.util.List;

import com.example.nkk.models.ProductCategory;
import com.example.nkk.repositories.ProductCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> listProductCategories() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategoryById(long id) {
        return productCategoryRepository.getById(id);
    }

    public void addProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    public ProductCategory editProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory(long id) {
        productCategoryRepository.deleteById(id);
    }

}
