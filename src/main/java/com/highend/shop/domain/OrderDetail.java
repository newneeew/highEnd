package com.highend.shop.domain;

import jakarta.persistence.*;

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
    Order order;

    @ManyToOne
    Product product;
}
