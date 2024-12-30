package com.highend.shop.controller;

import com.highend.shop.domain.OrderDetail;
import com.highend.shop.domain.Orders;
import com.highend.shop.dto.AddOrderDetailRequest;
import com.highend.shop.dto.OrderDetailResponse;
import com.highend.shop.dto.OrdersResponse;
import com.highend.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;

    /**
     * 주문 상세 목록 검색
     */
    @GetMapping("/api/order/detail/{orderId}")
    public ResponseEntity<List<OrderDetailResponse>> getOrderDetailList(@PathVariable long orderId) {
        List<OrderDetailResponse> orders = new ArrayList<>();
        List<OrderDetail> orderDetailList = orderService.getOrderDetailList(orderId);
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse(orderDetail);
            orders.add(orderDetailResponse);
        }
        return ResponseEntity.ok().body(orders);
    }

    /**
     * 주문 상세 추가 (주문이 없으면 새 주문 생성) => user관련된 것이 없음. user가 같을 때만 가져오는 것도 필요함
     */
    @PostMapping("/api/order/detail")
    public ResponseEntity<OrderDetailResponse> addOrCreateOrderDetail(@RequestBody AddOrderDetailRequest request) {
        OrderDetail orderDetail = orderService.addOrCreateOrderDetail(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderDetailResponse(orderDetail));
    }

    /**
     * 주문 완료 (결제 처리)
     */
    @PostMapping("/api/order/{orderId}")
    public ResponseEntity<OrdersResponse> completeOrder(@PathVariable long orderId) {
        Orders completedOrder = orderService.completeOrder(orderId);
        return ResponseEntity.ok(new OrdersResponse(completedOrder));
    }

    /**
     * 계산 시 총 가격 주문 return
     */
    @GetMapping("/api/order/{orderId}")
    public ResponseEntity<OrdersResponse> getOrder(@PathVariable long orderId) {
        Orders orders = orderService.getOrders(orderId);
        return ResponseEntity.ok().body(new OrdersResponse(orders));
    }
}