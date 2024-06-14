package com.jagadeesh.inventorymanagementsystem.controller;

import com.jagadeesh.inventorymanagementsystem.entity.Order;
import com.jagadeesh.inventorymanagementsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders/{userId}")
    public Order createOrder(@PathVariable Long userId) {
        return orderService.createOrder(userId);
    }

    @PutMapping("/orders/{id}")
    public Order addItemsToOrder(
                            @PathVariable Long id,
                            @RequestParam Long productId,
                            @RequestParam Integer quantity,
                            @RequestParam BigDecimal price){
        return orderService.addItemsToOrder(productId, id, quantity, price);
    }

    @GetMapping("/orders/{orderId}/total")
    public Map<String, Object> getOrderTotal(@PathVariable Long orderId) {
        BigDecimal bigDecimal = orderService.calculateTotalPrice(orderId);
        Map<String, Object> result = new HashMap<>();
        result.put("order_id", orderId);
        result.put("total_price", bigDecimal);
        return result;
    }
}
