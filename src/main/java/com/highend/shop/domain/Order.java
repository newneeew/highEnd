package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @CreatedDate
    @Column
    LocalDateTime orderDate;

    @Column(nullable = false)
    int total;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    OrderDetail orderDetail;
//    @ManyToOne
//    User user;
}
