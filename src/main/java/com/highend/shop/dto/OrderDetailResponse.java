package com.highend.shop.dto;

import com.highend.shop.domain.OrderDetail;
import lombok.Getter;

@Getter
public class OrderDetailResponse {
    private int subTotal;
    private int quantity;

    public OrderDetailResponse(OrderDetail orderDetail){
        this.subTotal = orderDetail.getSubTotal();
        this.quantity = orderDetail.getQuantity();
    }
}