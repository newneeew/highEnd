package com.highend.shop.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateVideoRequest {
    private String url;
    private String title;
    private LocalDateTime publish_at;
}
