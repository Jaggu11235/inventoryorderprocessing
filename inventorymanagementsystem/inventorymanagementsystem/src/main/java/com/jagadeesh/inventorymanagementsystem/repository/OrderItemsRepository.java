package com.jagadeesh.inventorymanagementsystem.repository;

import com.jagadeesh.inventorymanagementsystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

}
