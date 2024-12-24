package com.highend.shop.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String title;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime publish_at;

    @OneToMany(mappedBy = "video", cascade = CascadeType.REMOVE)
    private List<Product> productList;

    @Builder
    public Video(String url, String title, LocalDateTime publish_at) {
        this.url = url;
        this.title = title;
        this.publish_at = publish_at;
    }

    public void update(String url, String title, LocalDateTime publish_at) {
        this.url = url;
        this.title = title;
        this.publish_at = publish_at;
    }
}