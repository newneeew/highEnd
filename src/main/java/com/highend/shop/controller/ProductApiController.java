package com.highend.shop.controller;

import com.highend.shop.domain.Product;
import com.highend.shop.domain.Video;
import com.highend.shop.dto.AddLiveProductRequest;
import com.highend.shop.dto.ProductResponse;
import com.highend.shop.dto.UpdateProductRequest;
import com.highend.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductApiController {
    private final ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<Product> addProduct(@RequestBody AddLiveProductRequest request) {
        Product savedProduct = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProduct);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponse>> findAllProducts(boolean flag) {
        List<ProductResponse> products;
        if (flag == true) {
            products = productService.findAllLiveProduct()
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        } else {
            products = productService.findAllLimitedProduct()
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        }
        return ResponseEntity.ok()
                .body(products);
    }

    @GetMapping("/api/liveProducts")
    public ResponseEntity<List<ProductResponse>> findAllLiveProductsByVideo(long vid) {
        List<ProductResponse> products = new ArrayList<>();
        List<Product> productList = productService.findAllLiveProductsByVideo(vid);
        for(Product product : productList) {
            ProductResponse productResponse = new ProductResponse(product);
            products.add(productResponse);
        }
        return ResponseEntity.ok()
                .body(products);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok()
                .body(new ProductResponse(product));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,
                                                 @RequestBody UpdateProductRequest request) {
        Product updatedProduct = productService.update(id, request);
        return ResponseEntity.ok()
                .body(updatedProduct);
    }
}
