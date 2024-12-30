package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
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

//    @ManyToOne
//    User user;

    @Column(nullable = false)
    private boolean completed;

    @Builder
    public Orders (int total) {
        this.total = total;
    }
}
