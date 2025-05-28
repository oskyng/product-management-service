package com.example.productmanagement.service;

import com.example.productmanagement.dto.product.*;

public interface IProductService {
    GetProductsResponse getProducts(int page, String name);
    GetProductByIdResponse getProductById(Long id);
    CreateProductResponse createProduct(CreateProductRequest request);
    UpdateProductResponse updateProduct(Long id, CreateProductRequest request);
    DeleteProductResponse deleteProduct(Long id);
}
