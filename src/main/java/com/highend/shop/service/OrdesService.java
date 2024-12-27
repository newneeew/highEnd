package com.highend.shop.service;

import com.highend.shop.domain.Orders;
import com.highend.shop.dto.AddOrdersRequest;
import com.highend.shop.repository.OrderDetailRepository;
import com.highend.shop.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrdesService {
    private final OrdersRepository ordersRepository;
    private final OrderDetailRepository orderDetailRepository;

    public Orders save(AddOrdersRequest request) {
        return ordersRepository.save(request.toEntity());
    }

}
