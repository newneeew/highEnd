package com.highend.shop.dto;

import com.highend.shop.domain.OrderDetail;
import com.highend.shop.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AddOrderDetailRequest {
    private int quantity;
    private Product product;

    public OrderDetail toEntity(){
        return OrderDetail.builder()
                .quantity(quantity)
                .product(product)
                .build();
    }

    @Builder
    public AddOrderDetailRequest (int subTotal, int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
}