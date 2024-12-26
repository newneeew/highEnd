package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column
    private String img;

    @Column(nullable = false)
    private boolean limited;

    @Column(nullable = false)
    private int stock;

    @ManyToOne
    private Video video;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    List<OrderDetail> orderDetailList;

    @Builder
    public Product(String name, String description, int price, boolean limited, int stock, Video video) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.limited = limited;
        this.stock = stock;
        this.video = video;
    }

    public void update(String name, String description, int price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
