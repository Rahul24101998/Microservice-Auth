package com.demo.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSeverApplication.class, args);
	}

}
