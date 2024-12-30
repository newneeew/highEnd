package com.highend.shop.service;

import com.highend.shop.domain.OrderDetail;
import com.highend.shop.domain.Orders;
import com.highend.shop.domain.Product;
import com.highend.shop.dto.AddOrderDetailRequest;
import com.highend.shop.repository.OrderDetailRepository;
import com.highend.shop.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Transactional
    public OrderDetail addOrCreateOrderDetail(AddOrderDetailRequest request) {
        // 1. 진행 중인 주문 찾기 (completed = false)
        Orders order = ordersRepository.findFirstByCompletedFalseOrderByOrderDateDesc()
                .filter(o -> !o.isCompleted()) // 완료된 주문은 무시
                .orElseGet(() -> {
                    // 진행 중인 주문이 없으면 새 주문 생성
                    Orders newOrder = new Orders();
                    newOrder.setOrderDate(LocalDateTime.now());
                    newOrder.setTotal(0);
                    newOrder.setCompleted(false); // 새 주문은 항상 진행 중 상태로 생성
                    return ordersRepository.save(newOrder);
                });

        // 2. 상품 찾기
        Product product = request.getProduct();

        // 3. 주문 상세 생성
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrders(order);
        log.info("주문ID orderId: " + order.getId());
        orderDetail.setProduct(product);
        orderDetail.setQuantity(request.getQuantity());
        orderDetail.setSubTotal(product.getPrice() * request.getQuantity());

        // 4. 주문 상세 저장
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);

        // 5. 주문의 total 업데이트
        order.setTotal(order.getTotal() + orderDetail.getSubTotal());
        ordersRepository.save(order);

        return savedOrderDetail;
    }

    /**
     * 주문 완료 (결제 처리)
     */
    @Transactional
    public Orders completeOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));

        if (order.isCompleted()) {
            throw new IllegalStateException("Order is already completed.");
        }

        order.setCompleted(true); // 결제 완료 상태로 변경
        return ordersRepository.save(order);
    }

    public List<OrderDetail> getOrderDetailList(Long orderId) {
        Optional<Orders> orders = ordersRepository.findById(orderId);
        if (orders.isEmpty()) {
            return new ArrayList<>();
        }
        return orderDetailRepository.findAllByOrders(orders.get());
    }

    public Orders getOrders(Long orderId) {
        Optional<Orders> orders = ordersRepository.findById(orderId);
        if (orders.isEmpty()) {
            return new Orders();
        }
        return orders.get();
    }
}
