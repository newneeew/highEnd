package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Orders {
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
    List<OrderDetail> orderDetailList;
//    @ManyToOne
//    User user;
}
