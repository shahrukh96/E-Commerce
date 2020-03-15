package com.example.ecommerce.restcontroller;

import com.example.ecommerce.ApiUrls;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiUrls.ROOT_URL_ORDERS)

public class OrderRestController {
    private OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Order> orderList= orderService.findAll();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping(ApiUrls.URL_ORDERS_ORDER)
    public ResponseEntity<?> findOne(@PathVariable("orderId") long id) {
        if (!orderService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Order order = orderService.findOne(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Order order) {
        order = orderService.save(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(location).body(order);
    }

}
