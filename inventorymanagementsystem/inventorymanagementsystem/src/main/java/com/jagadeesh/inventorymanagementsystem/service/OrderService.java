package com.jagadeesh.inventorymanagementsystem.service;

import com.jagadeesh.inventorymanagementsystem.entity.Order;
import com.jagadeesh.inventorymanagementsystem.entity.OrderItem;
import com.jagadeesh.inventorymanagementsystem.entity.Product;
import com.jagadeesh.inventorymanagementsystem.entity.User;
import com.jagadeesh.inventorymanagementsystem.repository.OrderItemsRepository;
import com.jagadeesh.inventorymanagementsystem.repository.OrderRepository;
import com.jagadeesh.inventorymanagementsystem.repository.ProductRepository;
import com.jagadeesh.inventorymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public OrderService(OrderItemsRepository orderItemsRepository, OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Long userId) {
        Order order  = new Order();
        User user = userRepository.findById(userId).orElse(null);
        if(user != null) {
            order.setUserId(user);
        } else {
            throw new RuntimeException("User Not found");
        }
        order.setCreatedAt(new Date());
        order.setTotalPrice(BigDecimal.ZERO);
        return orderRepository.save(order);
    }

    public Order addItemsToOrder(Long productId, Long orderId, Integer quantity, BigDecimal price) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new RuntimeException("Order not found");
        } else {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                orderItem.setProduct(product);
            } else {
                throw new RuntimeException("Product out of stock");
            }
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setOrder(order);
            orderItemsRepository.save(orderItem);

            BigDecimal totalPrice = order.getTotalPrice().add(price.multiply(BigDecimal.valueOf(quantity)));
            order.setTotalPrice(totalPrice);
            Set<OrderItem> orderItemsSet = new HashSet<>();
            orderItemsSet.add(orderItem);
            order.setItems(orderItemsSet);
            orderRepository.save(order);
            return order;
        }
    }

    public BigDecimal calculateTotalPrice(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order == null) {
            throw new RuntimeException("Order not found");
        } else {
            return order.getTotalPrice();
        }
    }
}
