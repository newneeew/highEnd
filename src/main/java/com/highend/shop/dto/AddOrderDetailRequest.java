package com.highend.shop.dto;

import com.highend.shop.domain.OrderDetail;
import lombok.Getter;

@Getter
public class AddOrderDetailRequest {
    private int subTotal;
    private int quantity;

    public OrderDetail toEntity(){
        return OrderDetail.builder()
                .subTotal(subTotal)
                .quantity(quantity)
                .build();
    }
}