package com.highend.shop.dto;

import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class VideoResponse {
    private String url;
    private String title;
    private LocalDateTime publish_at;
    private LocalDateTime created_at;
    private List<Product> productList;

    public VideoResponse(Video video){
        this.url = video.getUrl();
        this.title = video.getTitle();
        this.publish_at = video.getPublish_at();
        this.created_at = video.getCreated_at();
        this.productList = video.getProductList();
    }
}