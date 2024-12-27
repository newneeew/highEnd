package com.highend.shop.dto;

import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddLiveProductRequest {
    private String name;
    private String description;
    private int price;
//    private String img;
    private boolean limited;
    private int stock;
    private Video video;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
//                .img(img)
                .limited(limited)
                .stock(stock)
                .video(video)
                .build();
    }
}
