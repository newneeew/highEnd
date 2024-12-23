package com.highend.shop.service;

import com.highend.shop.domain.Product;
import com.highend.shop.dto.AddLiveProductRequest;
import com.highend.shop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
//@Transactional // 테스트 데이터가 자동으로 롤백되도록 설정
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveProduct() {
        // Given
        AddLiveProductRequest request = new AddLiveProductRequest();
        // 요청 데이터를 설정합니다. 예: 이름, 가격 등
//        request.setName("Sample Product"); request.setPrice(1000);

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
}