package com.jagadeesh.inventorymanagementsystem.controller;

import com.jagadeesh.inventorymanagementsystem.entity.Product;
import com.jagadeesh.inventorymanagementsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestParam Integer quantity) {
        return productService.updateProduct(id, quantity);
    }

    @GetMapping("/inventory/value")
    public String getInventoryAmount() {
        return "total_inventory_value : " + productService.calculateTotalInventoryValue();
    }
}
