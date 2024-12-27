package com.highend.shop.dto;

import com.highend.shop.domain.OrderDetail;
import com.highend.shop.domain.Orders;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrdersResponse {
    private int total;
    private LocalDateTime orderDate;
    private List<OrderDetail> orderDetailList;

    public OrdersResponse(Orders orders){
        this.total = orders.getTotal();
        this.orderDate = orders.getOrderDate();
        this.orderDetailList = orders.getOrderDetailList();
    }
}