package com.demo.product_service.model.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String categoryId;

}