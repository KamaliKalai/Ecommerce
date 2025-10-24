package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.dao.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepository;

    // Add or Update Product
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    // Optional alias (to match controller)
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
