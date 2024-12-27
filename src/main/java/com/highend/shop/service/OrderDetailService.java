package com.highend.shop.service;

import com.highend.shop.domain.OrderDetail;
import com.highend.shop.dto.AddOrderDetailRequest;
import com.highend.shop.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetail save(AddOrderDetailRequest request) {
        return orderDetailRepository.save(request.toEntity());
    }

}
