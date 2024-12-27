package com.highend.shop.dto;

import com.highend.shop.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddOrdersRequest {
    private int total;

    public Orders toEntity() {
        return Orders.builder()
                .total(total)
                .build();
    }
}