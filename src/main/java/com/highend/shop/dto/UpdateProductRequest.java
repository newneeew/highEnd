package com.highend.shop.dto;

import lombok.Getter;

@Getter
public class UpdateProductRequest {
    private String name;
    private String description;
    private int price;
//    private String img;
    private int stock;
}
