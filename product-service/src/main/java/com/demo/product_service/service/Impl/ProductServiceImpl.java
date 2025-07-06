package com.demo.product_service.service.Impl;

import com.demo.product_service.model.entity.Products;
import com.demo.product_service.model.utils.ProductRequest;
import com.demo.product_service.repository.ProductRepository;
import com.demo.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public String addProduct(ProductRequest product) {
        // Convert ProductRequest to Products entity
        Products newProduct = new Products();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setCategoryId(product.getCategoryId());
        // Save the product to the repository
        Products savedProduct = repository.save(newProduct);

        return "Product added successfully with ID: " +savedProduct.getId();
    }

    @Override
    public void updateProduct(String id, ProductRequest product) {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public void getProductById(String id) {

    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public void getProductsByCategoryId(String categoryId) {

    }

    @Override
    public void getProductsByName(String name) {

    }

    @Override
    public void getProductsByPriceRange(double minPrice, double maxPrice) {

    }
}
