package com.highend.shop.repository;

import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.limited = true")
    List<Product> findAllLimitedTrue();

    List<Product> findAllByVideo(Video video);
}
