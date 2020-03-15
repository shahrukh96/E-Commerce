package com.example.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    private String email;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<Item> itemList = new HashSet<>();

}
