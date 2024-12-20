package com.highend.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vid", updatable = false)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "title", nullable = false)
    private String title;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "publishTime", nullable = false)
    private LocalDateTime publishTime;
}