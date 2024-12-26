package com.highend.shop.service;

import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import com.highend.shop.dto.AddLiveProductRequest;
import com.highend.shop.dto.UpdateProductRequest;
import com.highend.shop.repository.ProductRepository;
import com.highend.shop.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional // 테스트 데이터가 자동으로 롤백되도록 설정
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Test
    void saveProduct() {
        // Given
        AddLiveProductRequest request = new AddLiveProductRequest();
        // 요청 데이터를 설정합니다. 예: 이름, 가격 등
//        request.setName("Sample Product"); request.setPrice(1000);
//        request.setDescription("Description"); request.setStock(10);
//        request.setLimited(false);

        // When
        Product savedProduct = productService.save(request);

        // Then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(request.getName()); // 예시로 가정
        assertThat(savedProduct.getPrice()).isEqualTo(request.getPrice()); // 예시로 가정

        // 데이터베이스에 저장된 데이터 검증
        Product productFromDb = productRepository.findById(savedProduct.getId()).orElse(null);
        assertThat(productFromDb).isNotNull();
        assertThat(productFromDb.getName()).isEqualTo(request.getName()); // 예시로 가정
    }

    @Test
    void updateProduct() {
        UpdateProductRequest request = new UpdateProductRequest();
//        request.setName("상품2");
//        request.setDescription("설명2");
//        request.setPrice(1000);
//        request.setStock(3);

        Product updatedProduct = productService.update(1L, request);

        assertThat(updatedProduct).isNotNull();
        assertThat(updatedProduct.getId()).isNotNull();
        assertThat(updatedProduct.getName()).isEqualTo(request.getName()); // 예시로 가정
        assertThat(updatedProduct.getPrice()).isEqualTo(request.getPrice());
    }

    void findAllLiveProductsByVideo() {
        Video video = new Video();
//        video.setTitle("Sample Video");
        videoRepository.save(video);

        Product product1 = new Product();
//        product1.setName("Product 1");
//        product1.setVideo(video);
        productRepository.save(product1);

        Product product2 = new Product();
//        product2.setName("Product 2");
//        product2.setVideo(video);
        productRepository.save(product2);

        List<Product> productList = productService.findAllLiveProductsByVideo(1L);

        assertNotNull(productList);  // 반환된 리스트가 null이 아님을 확인
        assertEquals(2, productList.size());  // Product가 2개가 되어야 함
        assertTrue(productList.contains(product1));  // 첫 번째 Product가 포함되어야 함
        assertTrue(productList.contains(product2));
    }
}