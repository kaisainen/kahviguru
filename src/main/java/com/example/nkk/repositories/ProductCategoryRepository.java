package com.example.nkk.repositories;

import com.example.nkk.models.ProductCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findByName(String name);
}
