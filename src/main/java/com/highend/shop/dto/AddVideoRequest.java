package com.highend.shop.dto;

import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddVideoRequest {
    private String url;
    private String title;
    private LocalDateTime publish_at;

    public Video toEntity() {
        return Video.builder()
                .url(url)
                .title(title)
                .publish_at(publish_at)
                .build();
    }
}