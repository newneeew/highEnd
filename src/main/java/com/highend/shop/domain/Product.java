package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", updatable = false)
    private Long id;

    @Column(name = "pname", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "limited", nullable = false)
    private boolean limited;

    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne
    private Video video;

    @Builder
    public Product(String name, String description, int price, boolean limited, int stock, Video video) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.limited = limited;
        this.stock = stock;
        this.video = video;
    }

}
