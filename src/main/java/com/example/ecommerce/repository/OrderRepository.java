package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByEmail(String email);
}
