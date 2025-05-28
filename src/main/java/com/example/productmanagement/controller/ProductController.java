package com.example.productmanagement.controller;

import com.example.productmanagement.dto.product.CreateProductRequest;
import com.example.productmanagement.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProductController implements IProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<Object> getProducts(int page, String name) {
        return ResponseEntity.ok(productService.getProducts(page, name));
    }

    @Override
    public ResponseEntity<Object> getProductById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> createProduct(CreateProductRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateProduct(Long id, CreateProductRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> DeleteProduct(Long id) {
        return null;
    }
}
