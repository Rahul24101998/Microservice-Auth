package com.demo.product_service.controller;


import com.demo.product_service.model.utils.ProductRequest;
import com.demo.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductService service;

    // Define your endpoints here
    // For example:
    // @GetMapping("/products")
    // public List<Product> getAllProducts() {
    //     return productService.getAllProducts();
    // }

     @PostMapping("/addProducts")
     public String createProduct(@RequestBody ProductRequest product) {
         return service.addProduct(product);
     }

    // Add more methods as needed for your product service
}
