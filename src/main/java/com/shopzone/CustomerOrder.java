package com.shopzone;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private double totalAmount;
    private int itemCount;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CONFIRMED;
    
    private LocalDateTime orderDate = LocalDateTime.now();

    public CustomerOrder() {}
    public CustomerOrder(String username, double totalAmount, int itemCount) {
        this.username = username;
        this.totalAmount = totalAmount;
        this.itemCount = itemCount;
    }
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public double getTotalAmount() { return totalAmount; }
    public int getItemCount() { return itemCount; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getOrderDate() { return orderDate; }
}