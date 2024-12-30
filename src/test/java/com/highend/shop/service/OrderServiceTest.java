package com.highend.shop.service;

import com.highend.shop.domain.OrderDetail;
import com.highend.shop.domain.Orders;
import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import com.highend.shop.dto.AddOrderDetailRequest;
import com.highend.shop.repository.ProductRepository;
import com.highend.shop.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void addOrder() {
        Video video = new Video();
        video.setUrl("test URL");
        video.setTitle("title");
        video.setPublish_at(LocalDateTime.now().plusDays(3));
        videoRepository.save(video);

        Product product1 = Product.builder()
                .description("description1")
                .price(1000)
                .name("name1")
                .stock(10)
                .limited(false)
                .video(video)
                .build();
        productRepository.save(product1);
        Product product2 = Product.builder()
                .description("description2")
                .price(1000)
                .name("name2")
                .stock(10)
                .limited(false)
                .video(video)
                .build();
        productRepository.save(product2);
        log.info("productID: " + product1.getId());

        AddOrderDetailRequest request1 = AddOrderDetailRequest.builder()
                .quantity(1)
                .product(product1)
                .build();

        AddOrderDetailRequest request2 = AddOrderDetailRequest.builder()
                .quantity(2)
                .product(product2)
                .build();
        OrderDetail orderDetail1 = orderService.addOrCreateOrderDetail(request1);
        OrderDetail orderDetail2 = orderService.addOrCreateOrderDetail(request2);

        Orders orders1 = orderDetail1.getOrders();
        Orders orders2 = orderDetail2.getOrders();
        assertThat(orderDetail1).isNotNull();
        assertThat(orderDetail2).isNotNull();

        assertThat(orders1).isNotNull();
        assertThat(orders2).isNotNull();

        log.info("주문1 orders1: " + orders1);
        log.info("주문2 orders2: " + orders2);
        assertEquals(orders1.getId(), orders2.getId());
        orders2.setCompleted(true);
    }
}