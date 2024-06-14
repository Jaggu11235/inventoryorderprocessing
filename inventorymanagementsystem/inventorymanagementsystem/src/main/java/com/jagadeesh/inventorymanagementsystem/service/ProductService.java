package com.jagadeesh.inventorymanagementsystem.service;

import com.jagadeesh.inventorymanagementsystem.entity.Product;
import com.jagadeesh.inventorymanagementsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Integer quantity) {
        Product product = productRepository.findById(id).orElse(null);
        if(product != null) {
            product.setQuantity(quantity);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found in DB");
        }
    }

    public BigDecimal calculateTotalInventoryValue() {
        List<Product> products = productRepository.findAll();
        BigDecimal inventoryValue = new BigDecimal(0);
        for(Product product : products){
            inventoryValue = inventoryValue.add(product.getPrice().multiply(new BigDecimal(product.getQuantity())));
        }
        return inventoryValue;
    }
}
