package com.demo.product_service.repository;

import com.demo.product_service.model.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,String> {
    Optional <Products> findById(String id);
    Optional <Products> findByName(String name);
    Optional <Products> findByCategoryId(String categoryId);

    Products save(Products product);
    void deleteById(String id);
    void delete(Products product);
}
