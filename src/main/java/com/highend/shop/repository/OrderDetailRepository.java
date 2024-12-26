package com.highend.shop.repository;

import com.highend.shop.domain.OrderDetail;
import com.highend.shop.domain.Orders;
import com.highend.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByProduct(Product product);
    List<OrderDetail> findAllByOrders(Orders orders);
}
