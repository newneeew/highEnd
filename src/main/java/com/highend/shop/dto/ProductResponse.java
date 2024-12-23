package com.highend.shop.dto;

import com.highend.shop.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponse {
    private String name;
    private String description;
    private int price;
//    private String img;

    public ProductResponse(Product product){
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
//        this.img = product.getImg();
    }
}
