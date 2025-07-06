package com.demo.product_service.service;

import com.demo.product_service.model.utils.ProductRequest;

public interface ProductService {

    public String addProduct(ProductRequest product);
    public void updateProduct(String id, ProductRequest product);
    public void deleteProduct(String id);
    public void getProductById(String id);
    public void getAllProducts();
    public void getProductsByCategoryId(String categoryId);
    public void getProductsByName(String name);
    public void getProductsByPriceRange(double minPrice, double maxPrice);
}
