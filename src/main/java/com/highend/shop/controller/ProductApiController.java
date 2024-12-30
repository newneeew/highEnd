package com.highend.shop.controller;

import com.highend.shop.domain.Product;
import com.highend.shop.dto.AddLiveProductRequest;
import com.highend.shop.dto.ProductResponse;
import com.highend.shop.dto.UpdateProductRequest;
import com.highend.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ProductApiController {
    private final ProductService productService;

    @GetMapping("/api/limitedProducts")
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        List<ProductResponse> products = productService.findAllLimitedProduct()
                    .stream()
                    .map(ProductResponse::new)
                    .toList();
        return ResponseEntity.ok()
                .body(products);
    }

    @PostMapping("/api/liveProducts") // 현재 product 안에 비디오를 가져와서 저장하게 해놨는데 vid로 가져와서 할 건지
    public ResponseEntity<Product> addProduct(@RequestBody AddLiveProductRequest request) {
        log.info("여기서부터 시작!");
        log.info("request: " + request.toString());
        Product savedProduct = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedProduct);
    }

    @GetMapping("/api/liveProducts") //PathVariable과 RequestParam 중에 뭐가 더 나은지 물어보기
    public ResponseEntity<List<ProductResponse>> findAllLiveProductsByVideo(@RequestParam long vid) {
        List<ProductResponse> products = new ArrayList<>();
        List<Product> productList = productService.findAllLiveProductsByVideo(vid);
        for(Product product : productList) {
            ProductResponse productResponse = new ProductResponse(product);
            products.add(productResponse);
        }
        return ResponseEntity.ok()
                .body(products);
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ProductResponse> findProduct(@PathVariable long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok()
                .body(new ProductResponse(product));
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id,
                                                 @RequestBody UpdateProductRequest request) {
        Product updatedProduct = productService.update(id, request);
        return ResponseEntity.ok()
                .body(updatedProduct);
    }
}
