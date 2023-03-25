package com.example.nkk.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.nkk.models.Product;
import com.example.nkk.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(long id) {
        return productRepository.getById(id);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> getProductsPageable(int currentPage, int amount, List<Long> productCategoryNumbers, String searchTerm) {
        Pageable pageable = PageRequest.of(currentPage, amount);
        return productRepository.getProducts(searchTerm.toLowerCase(), productCategoryNumbers, pageable);
    }

    public Page<Product> getProductsByProductCategory(int currentPage, int amount, List<Long> productCategoryNumbers) {
        Pageable pageable = PageRequest.of(currentPage, amount);
        return productRepository.findByProductCategory_IdIn(productCategoryNumbers, pageable);
    }

    public void editProduct(Optional<Product> product) {
    }

}
