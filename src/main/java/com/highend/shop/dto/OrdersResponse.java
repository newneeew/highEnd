package com.highend.shop.dto;

import com.highend.shop.domain.Orders;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrdersResponse {
    private int total;
    private LocalDateTime orderDate;

    public OrdersResponse(Orders orders){
        this.total = orders.getTotal();
        this.orderDate = orders.getOrderDate();
    }
}