package com.example.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    protected Date createdAt;

    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_item",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")}
    )
    private Set<Item> itemList = new HashSet<>();

    @Transient
    private Set<Long> itemIds = new HashSet<>();

}
