package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Setter
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

    @OneToMany(mappedBy = "orders", cascade = CascadeType.REMOVE)
    List<OrderDetail> orderDetailList;
//    @ManyToOne
//    User user;
}
