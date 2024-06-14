package com.jagadeesh.inventorymanagementsystem.repository;

import com.jagadeesh.inventorymanagementsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
