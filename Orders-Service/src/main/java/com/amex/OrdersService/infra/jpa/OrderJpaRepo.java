package com.amex.OrdersService.infra.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amex.OrdersService.infra.Order;

@Repository
public interface OrderJpaRepo extends JpaRepository<Order, Integer> {
    public Order findByOrderId(int orderId);
    public List<Order> findAll();
}