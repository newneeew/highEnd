package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    int subtitle;

    @Column(nullable = false)
    int quantity;

    @ManyToOne
    Orders order;

    @ManyToOne
    Product product;
}
