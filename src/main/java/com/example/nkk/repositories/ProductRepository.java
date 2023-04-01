package com.example.nkk.repositories;

import java.util.Collection;
import java.util.List;

import com.example.nkk.models.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

        Page<Product> findByProductCategory_IdIn(Collection<Long> products, Pageable pageable);

        @Query("SELECT p FROM Product p WHERE lower(p.name) LIKE %:searchTerm%"
                        + " AND p.productCategory.id IN :productCategories")
        public Page<Product> getProducts(@Param("searchTerm") String searchTerm, @Param("productCategories") List<Long> productCategories,
                        Pageable pageable);

}
