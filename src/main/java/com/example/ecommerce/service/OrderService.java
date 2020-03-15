package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.repository.OrderRepository;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findOne(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }


    public boolean exists(Long id) {
        return orderRepository.existsById(id);
    }
}
