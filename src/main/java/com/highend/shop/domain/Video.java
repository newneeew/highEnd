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