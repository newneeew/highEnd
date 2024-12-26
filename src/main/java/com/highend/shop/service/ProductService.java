package com.highend.shop.service;

import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import com.highend.shop.dto.AddLiveProductRequest;
import com.highend.shop.dto.UpdateProductRequest;
import com.highend.shop.repository.ProductRepository;
import com.highend.shop.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final VideoRepository videoRepository;

    public Product save(AddLiveProductRequest request) {
        return productRepository.save(request.toEntity());
    }

    public List<Product> findAllLiveProduct() {
        List productList = productRepository.findAllLimitedFalse();
        if (productList.isEmpty()) {
            return null;
        }
        return productList;
    }

    public List<Product> findAllLimitedProduct() {
        return productRepository.findAllLimitedTrue();
    }

    public List<Product> findAllLiveProductsByVideo(Long vid) {
        Optional<Video> video = videoRepository.findById(vid);
        if (video.isEmpty()) {
            return new ArrayList<>();  // 빈 리스트 반환
        }
        return productRepository.findAllByVideo(video.get());
    }

    public Product findById(long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public Product update(long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id).orElse(null);
        product.update(request.getName(), request.getDescription(), request.getPrice(), request.getStock());
        return product;
    }
}
