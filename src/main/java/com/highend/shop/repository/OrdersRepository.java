package com.highend.shop.repository;

import com.highend.shop.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findFirstByCompletedFalseOrderByOrderDateDesc();
}
