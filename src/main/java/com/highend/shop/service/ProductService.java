package com.highend.shop.service;

import com.highend.shop.domain.Product;
import com.highend.shop.dto.AddLiveProductRequest;
import com.highend.shop.dto.UpdateProductRequest;
import com.highend.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(AddLiveProductRequest request) {
        return productRepository.save(request.toEntity());
    }

    public List<Product> findAllLiveProduct() {
        return productRepository.findAllLimitedFalse();
    }

    public List<Product> findAllLimitedProduct() {
        return productRepository.findAllLimitedTrue();
    }

    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found product: " + id));
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public Product update(long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found product: " + id));
        product.update(request.getName(), request.getDescription(), request.getPrice(), request.getStock());
        return product;
    }
}
