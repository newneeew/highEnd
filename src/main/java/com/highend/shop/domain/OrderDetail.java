package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    int subTotal;

    @Column(nullable = false)
    int quantity;

    @ManyToOne
    Orders orders;

    @ManyToOne
    Product product;

    @Builder
    public OrderDetail (int subTotal, int quantity) {
        this.subTotal = subTotal;
        this.quantity = quantity;
    }
}
