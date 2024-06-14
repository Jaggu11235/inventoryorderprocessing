package com.jagadeesh.inventorymanagementsystem.repository;

import com.jagadeesh.inventorymanagementsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
