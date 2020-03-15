package com.example.ecommerce.service;

import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.repository.ItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findOne(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order findByEmail(String email) {
        return orderRepository.findByEmail(email);
    }

    @Transactional
    public Order save(Order order) {
        List<Item> itemList = itemRepository.findAllById(order.getItemIds());
        order.getItemList().addAll(itemList);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return orderRepository.existsById(id);
    }
}
